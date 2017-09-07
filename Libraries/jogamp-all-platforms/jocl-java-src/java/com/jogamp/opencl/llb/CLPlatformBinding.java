/* !---- DO NOT EDIT: This file autogenerated by com/jogamp/gluegen/JavaEmitter.java on Sat Oct 10 04:57:33 CEST 2015 ----! */

package com.jogamp.opencl.llb;

import com.jogamp.gluegen.runtime.*;
import com.jogamp.common.os.*;
import com.jogamp.common.nio.*;
import java.nio.*;

 /**
  * Java bindings to OpenCL platforms.
  * @author Michael Bien, GlueGen, et al.
  */
public interface CLPlatformBinding {

  /** CType: int */
  public static final int CL_PLATFORM_VERSION = 0x901;
  /** CType: int */
  public static final int CL_PLATFORM_VENDOR = 0x903;
  /** CType: int */
  public static final int CL_PLATFORM_NOT_FOUND_KHR = -1001;
  /** CType: int */
  public static final int CL_PLATFORM_PROFILE = 0x900;
  /** CType: int */
  public static final int CL_PLATFORM_EXTENSIONS = 0x904;
  /** CType: int */
  public static final int CL_PLATFORM_NAME = 0x902;
  /** CType: int */
  public static final int CL_PLATFORM_ICD_SUFFIX_KHR = 0x920;

  /** Interface to C language function: <br> <code>cl_int {@native clGetPlatformIDs}(cl_uint num_entries, cl_platform_id *  platforms, cl_uint *  num_platforms)</code><br>
      @param platforms a direct only {@link com.jogamp.common.nio.PointerBuffer}
      @param num_platforms a direct only {@link java.nio.IntBuffer}   */
  public int clGetPlatformIDs(int num_entries, PointerBuffer platforms, IntBuffer num_platforms);

  /** Interface to C language function: <br> <code>cl_int {@native clGetPlatformInfo}(cl_platform_id platform, cl_platform_info param_name, size_t param_value_size, void *  param_value, size_t *  param_value_size_ret)</code><br>
      @param param_value a direct only {@link java.nio.Buffer}
      @param param_value_size_ret a direct only {@link com.jogamp.common.nio.PointerBuffer}   */
  public int clGetPlatformInfo(long platform, int param_name, long param_value_size, Buffer param_value, PointerBuffer param_value_size_ret);

  /** Interface to C language function: <br> <code>cl_int {@native clGetDeviceIDs}(cl_platform_id platform, cl_device_type device_type, cl_uint num_entries, cl_device_id *  devices, cl_uint *  num_devices)</code><br>
      @param devices a direct only {@link com.jogamp.common.nio.PointerBuffer}
      @param num_devices a direct only {@link java.nio.IntBuffer}   */
  public int clGetDeviceIDs(long platform, long device_type, int num_entries, PointerBuffer devices, IntBuffer num_devices);

  /** Interface to C language function: <br> <code>cl_int {@native clIcdGetPlatformIDsKHR}(cl_uint, cl_platform_id * , cl_uint * )</code><br>
      @param arg1 a direct only {@link com.jogamp.common.nio.PointerBuffer}
      @param arg2 a direct only {@link java.nio.IntBuffer}   */
  public int clIcdGetPlatformIDsKHR(int arg0, PointerBuffer arg1, IntBuffer arg2);


} // end of class CLPlatformBinding