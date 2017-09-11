package com.hyh0.gmath;

import static com.jogamp.opencl.CLMemory.Mem.READ_WRITE;

import java.nio.FloatBuffer;

import com.hyh0.gmath.debug.Tools;
import com.jogamp.opencl.CLBuffer;
import com.jogamp.opencl.CLCommandQueue;
import com.jogamp.opencl.CLContext;

public class Matrix {

    public final int M;
    public final int N;
    private CLBuffer<FloatBuffer> matrixBuffer;
    private CLCommandQueue queue;
    private CLContext context;
    
    /**
     * 创建一个新矩阵
     * @param context 一个OpenCl的context
     * @param queue context对应的queue
     * @param m 矩阵的行数
     * @param n 矩阵的列数
     */
    public Matrix(CLContext context, CLCommandQueue queue, int m, int n) {
        this.matrixBuffer = context.createFloatBuffer(m * n, READ_WRITE);
        this.queue = queue;
        this.M = m;
        this.N = n;
        this.context = context;
        syncToDevice();
    }
    
    /**
     * 创建一个新矩阵
     * @param context 一个OpenCl的context
     * @param queue context对应的queue
     * @param data 储存数据的二维数组
     */
    public Matrix(CLContext context, CLCommandQueue queue, double[][] data) {
        int m = data.length;
        int n = data[0].length;
        this.matrixBuffer = context.createFloatBuffer(m * n, READ_WRITE);
        this.queue = queue;
        this.M = m;
        this.N = n;
        this.context = context;
        this.syncToDevice();
        this.set(data);
    }
    
    /**
     * 设置矩阵的元素,并同步到显存
     * @param m 第m行
     * @param n 第n列
     * @param data 要修改成的数据
     */
    public void set(int m, int n, double data) {
        if(m >= this.M || n >= this.N)
            throw newIllegalArgumentException("超出矩阵范围");
        
        int targetPosition = m * this.N + n;
        FloatBuffer buffer = matrixBuffer.getBuffer();
        buffer.position(targetPosition);
        buffer.put((float)data);
        this.syncToDevice();
    }
    
    public void set(double[][] data) {
        if(data.length != this.M || data[0].length != this.N)
            throw newIllegalArgumentException("数组和矩阵不符");
        FloatBuffer buffer = matrixBuffer.getBuffer();
        for(double[] vs : data) {
            for(double v : vs) {
                buffer.put((float)v);
            }
        }
        this.syncToDevice();
    }
    
    public double get(int m, int n) {
        if(m >= this.M || n >= this.N)
            throw newIllegalArgumentException("超出矩阵范围");
        this.syncFromDevice();
        int targetPosition = m * this.N + n;
        return matrixBuffer.getBuffer().get(targetPosition);
    }
    
    public double[][] get() {
        this.syncFromDevice();
        FloatBuffer buffer = matrixBuffer.getBuffer();
        double[][] result = new double[M][N];
        for (int m = 0; m < M; m++) {
            for (int n = 0; n < N; n++) {
                result[m][n] = buffer.get();
            }
        }
        return result;
    }
    
    protected CLBuffer<FloatBuffer> getArg() {
        return matrixBuffer;
    }
    
    @Override
    public String toString() {
        this.syncFromDevice();
        FloatBuffer buffer = matrixBuffer.getBuffer();
        buffer.position(0);
        String result = "[";
        for(int m = 0; m < M; m++) {
            if(m != 0)
                result += " ";
            result += "[";
            for(int n = 0; n < N; n++) {
                result += String.format("%7.2f", buffer.get());
                if(n != N - 1)
                    result += ", ";
            }
            result += "]";
            if(m != M - 1)
                result += "\n";;
        }
        result += "]\n";
        return result;
    }
    /**
     * 将数据从主机端同步到设备端
     */
    public void syncToDevice() {
        matrixBuffer.getBuffer().position(0);
        queue.putWriteBuffer(matrixBuffer, true);
    }
    
    /**
     * 将数据从设备端同步到主机端
     */
    public void syncFromDevice() {
        matrixBuffer.getBuffer().position(0);
        queue.putReadBuffer(matrixBuffer, true);
    }
    
    /**
     * 释放显存空间
     */
    public void release() {
        matrixBuffer.release();
    }
    
    /**
     * 重载了Object的finalize方法
     * 会调用release方法释放资源
     * @throws Throwable 抛出超类的异常
     */
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        this.release();
    }
    
    /**
     * 创建不合法参数异常，同时释放资源
     * @param message 包含的信息
     * @return IllegalArgument异常
     */
    private IllegalArgumentException newIllegalArgumentException(String message) {
        context.release();
        Tools.println("context被成功释放");
        return new IllegalArgumentException(message);
    }
}