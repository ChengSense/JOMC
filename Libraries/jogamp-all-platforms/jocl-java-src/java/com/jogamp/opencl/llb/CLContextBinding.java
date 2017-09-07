/* !---- DO NOT EDIT: This file autogenerated by com/jogamp/gluegen/JavaEmitter.java on Sat Oct 10 04:56:11 CEST 2015 ----! */

package com.jogamp.opencl.llb;

import com.jogamp.opencl.llb.impl.CLImageFormatImpl;
import com.jogamp.opencl.CLErrorHandler;
import com.jogamp.gluegen.runtime.*;
import com.jogamp.common.os.*;
import com.jogamp.common.nio.*;
import java.nio.*;

 /**
  * Java bindings to OpenCL contexts.
  * @author Michael Bien, GlueGen, et al.
  */
public interface CLContextBinding {

  /** CType: int */
  public static final int CL_CONTEXT_NUM_DEVICES = 0x1083;
  /** CType: int */
  public static final int CL_CONTEXT_PLATFORM = 0x1084;
  /** CType: int */
  public static final int CL_CONTEXT_REFERENCE_COUNT = 0x1080;
  /** CType: int */
  public static final int CL_CONTEXT_DEVICES = 0x1081;
  /** CType: int */
  public static final int CL_CONTEXT_PROPERTIES = 0x1082;

  /** Interface to C language function: <br> <code>cl_int {@native clRetainContext}(cl_context context)</code><br>   */
  public int clRetainContext(long context);

  /** Interface to C language function: <br> <code>cl_int {@native clReleaseContext}(cl_context context)</code><br>   */
  public int clReleaseContext(long context);

  /** Interface to C language function: <br> <code>cl_int {@native clGetContextInfo}(cl_context context, cl_context_info param_name, size_t param_value_size, void *  param_value, size_t *  param_value_size_ret)</code><br>
      @param param_value a direct only {@link java.nio.Buffer}
      @param param_value_size_ret a direct only {@link com.jogamp.common.nio.PointerBuffer}   */
  public int clGetContextInfo(long context, int param_name, long param_value_size, Buffer param_value, PointerBuffer param_value_size_ret);

  /** Interface to C language function: <br> <code>cl_int {@native clGetSupportedImageFormats}(cl_context context, cl_mem_flags flags, cl_mem_object_type image_type, cl_uint num_entries, cl_image_format *  image_formats, cl_uint *  num_image_formats)</code><br>
      @param num_image_formats a direct or array-backed {@link java.nio.IntBuffer}   */
  public int clGetSupportedImageFormats(long context, long flags, int image_type, int num_entries, CLImageFormatImpl image_formats, IntBuffer num_image_formats);

  /** Interface to C language function: <br> <code>cl_int {@native clGetSupportedImageFormats}(cl_context context, cl_mem_flags flags, cl_mem_object_type image_type, cl_uint num_entries, cl_image_format *  image_formats, cl_uint *  num_image_formats)</code><br>   */
  public int clGetSupportedImageFormats(long context, long flags, int image_type, int num_entries, CLImageFormatImpl image_formats, int[] num_image_formats, int num_image_formats_offset);


  // --- Begin CustomJavaCode .cfg declarations

  /** Interface to C language function: <br> <code> cl_context {@native clCreateContext}(intptr_t * , uint32_t, cl_device_id * , void (*pfn_notify)(const char *, const void *, size_t, void *), void *, int32_t * ); </code>    */
  public long clCreateContext(PointerBuffer properties, PointerBuffer devices, CLErrorHandler pfn_notify, IntBuffer errcode_ret);

  /** Interface to C language function: <br> <code> cl_context {@native clCreateContextFromType}(cl_context_properties *properties, cl_device_type device_type, void (*pfn_notify)(const char *errinfo,  const void *private_info, size_t cb, void *user_data), void *user_data, cl_int *errcode_ret) ; </code>    */
  public long clCreateContextFromType(PointerBuffer properties, long device_type, CLErrorHandler pfn_notify, IntBuffer errcode_ret);
  // ---- End CustomJavaCode .cfg declarations

} // end of class CLContextBinding