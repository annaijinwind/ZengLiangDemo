//
// Created by WP-FENGJQ on 2018/2/12.
//

#ifndef BSDIFFPATCH_BS_H
#define BSDIFFPATCH_BS_H
#endif //BSDIFFPATCH_BS_H
#include <malloc.h>
#include <jni.h>
int mydiff(int argc,char *argv[]);
int mypatch(int argc,char * argv[]);
JNIEXPORT jint JNICALL
Java_com_example_lebronsn_bsdiffpatch_MainActivity_patch
        (JNIEnv *env, jobject instance, jstring oldpath_, jstring newpath_,jstring patch_);
JNIEXPORT jint JNICALL
Java_com_example_lebronsn_bsdiffpatch_MainActivity_diff
        (JNIEnv *env, jobject instance, jstring oldpath_, jstring newpath_, jstring patch_);
