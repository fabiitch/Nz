package com.github.fabiitch.nz.gdx.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import lombok.experimental.UtilityClass;

import java.io.File;
import java.util.Objects;

@UtilityClass
public final class GdxInternalCopy {

    /**
     * Copy an INTERNAL folder/file (assets) to a real OS File location.
     *
     * If internalFolder is a folder: copies recursively into 'file' as a directory.
     * If internalFolder is a file: copies into 'file' as a file.
     *
     * @param internalFolder path inside assets (ex: "gstreamer" or "gstreamer/bin/gst-launch.exe")
     * @param file destination on disk (directory or file)
     * @return the destination File (same reference as provided)
     */
    public static File copyInternalToFile(String internalFolder, File file) {
        Objects.requireNonNull(internalFolder, "internalFolder");
        Objects.requireNonNull(file, "file");

        FileHandle src = Gdx.files.internal(internalFolder);

        if (!src.exists()) {
            throw new IllegalArgumentException("Internal path does not exist: " + internalFolder);
        }

        if (src.isDirectory()) {
            // Destination must be a directory
            if (!file.exists()) {
                boolean ok = file.mkdirs();
                if (!ok && !file.exists()) {
                    throw new IllegalStateException("Failed to create destination directory: " + file.getAbsolutePath());
                }
            } else if (!file.isDirectory()) {
                throw new IllegalArgumentException("Destination must be a directory when copying a folder. Dest="
                        + file.getAbsolutePath());
            }

            copyDirRecursive(src, file);
            return file;
        }

        // Source is a file: ensure parent exists
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            boolean ok = parent.mkdirs();
            if (!ok && !parent.exists()) {
                throw new IllegalStateException("Failed to create parent directory: " + parent.getAbsolutePath());
            }
        }

        // Copy file
        FileHandle dst = Gdx.files.absolute(file.getAbsolutePath());
        src.copyTo(dst);
        return file;
    }

    private static void copyDirRecursive(FileHandle srcDir, File dstDir) {
        // srcDir is INTERNAL directory
        FileHandle[] children = srcDir.list();

        // In some packagings list() might be empty; but on desktop assets it's fine.
        for (FileHandle child : children) {
            File dst = new File(dstDir, child.name());

            if (child.isDirectory()) {
                if (!dst.exists()) {
                    boolean ok = dst.mkdirs();
                    if (!ok && !dst.exists()) {
                        throw new IllegalStateException("Failed to create directory: " + dst.getAbsolutePath());
                    }
                }
                copyDirRecursive(child, dst);
            } else {
                // Ensure parent exists
                File parent = dst.getParentFile();
                if (parent != null && !parent.exists()) {
                    boolean ok = parent.mkdirs();
                    if (!ok && !parent.exists()) {
                        throw new IllegalStateException("Failed to create directory: " + parent.getAbsolutePath());
                    }
                }

                FileHandle dstHandle = Gdx.files.absolute(dst.getAbsolutePath());
                child.copyTo(dstHandle);
            }
        }
    }
}
