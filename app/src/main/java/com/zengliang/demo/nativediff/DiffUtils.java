package com.zengliang.demo.nativediff;

/**
 * Created by WP-FENGJQ on 2018/2/12.
 */

public class DiffUtils {
    static {
        System.loadLibrary("diff");
    }
    /**
     * native方法 比较路径为oldPath的apk与newPath的apk之间差异，并生成patch包，存储于patchPath
     * <p>
     * 返回：0，说明操作成功
     *
     * @param oldApkPath 老apk
     * @param newApkPath 新apk
     * @param patchPath 补丁包
     * @return
     */
    public static native String getDiffPatch(String oldApkPath, String newApkPath, String patchPath);
}
