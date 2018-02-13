#include <jni.h>
/* Header for class com_zengliang_demo_nativediff_DiffUtils */

#ifndef _Included_com_zengliang_demo_nativediff_PatchUtils
#define _Included_com_zengliang_demo_nativediff_PatchUtils
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     sven_com_apkpatchserver_DiffUtils
 * Method:    genDiff
 * Signature: (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_com_zengliang_demo_nativepatch_PatchUtils_getNewApk
  (JNIEnv *, jclass, jstring, jstring, jstring);




#ifdef __cplusplus
}
#endif
#endif