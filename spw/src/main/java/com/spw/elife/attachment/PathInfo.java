package com.spw.elife.attachment;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.util.Iterator;

public class PathInfo {
 
    private final File file;
    private final String url;

    public PathInfo(File file, String url) {
        this.file = file;
        this.url = url;
    }

    public File getFile() {
        return file;
    }

    public String getUrl() {
        return url;
    }

    public boolean isLocalResource() {
        return file != null;
    }

    public boolean isFileExists() {
        return file != null && file.exists();
    }

    public Iterator<PathInfo> childrenIterator() {
        return new PathInfoInterator(this);
    }

    private class PathInfoInterator implements Iterator<PathInfo> {

        private final File[] files;
        private final String path;
        private int pointer = 0;

        public PathInfoInterator(PathInfo pathInfo) {
            this.files = pathInfo.isFileExists() ? pathInfo.getFile().listFiles() : new File[0];
            this.path = pathInfo.getUrl();
        }

        public boolean hasNext() {
            return pointer < files.length;
        }

        public PathInfo next() {
            File f=files[pointer++];
            return new PathInfo(f,path+"/"+f.getName());
        }

		public void remove() {
			
		}
    }
}
