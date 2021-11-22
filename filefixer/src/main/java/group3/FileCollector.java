package group3;

import java.io.*;
import java.util.*;
import java.util.zip.*;

public class FileCollector {
    private Collection<File> folders;
    private Collection<File> files;
    private File csv = null;
    private File newDest;

    public FileCollector() {
        files = new ArrayList<>();
        folders = new ArrayList<>();

    }

    public File writeToFiles(File srcFile, String zipPath) {
        ZipFile zipfile = null;
        try {
            zipfile = new ZipFile(srcFile);
            Enumeration<? extends ZipEntry> e = zipfile.entries();
            while (e.hasMoreElements()) {
                ZipEntry entry = e.nextElement();
                newDest = new File(zipPath, entry.getName());
                if (!newDest.getParent().contains("MACOSX")) {
                    newDest.getParentFile().mkdirs();
                    if (entry.isDirectory()) {
                        continue;
                    } else {
                        BufferedInputStream bis = new BufferedInputStream(zipfile.getInputStream(entry));
                        int b;
                        byte buffer[] = new byte[1024];
                        FileOutputStream fos = new FileOutputStream(newDest);
                        BufferedOutputStream bos = new BufferedOutputStream(fos, 1024);
                        while ((b = bis.read(buffer, 0, 1024)) != -1) {
                            bos.write(buffer, 0, b);
                        }
                        bos.close();
                        bis.close();
                    }
                }

            }

        } catch (IOException ioe) {
            System.out.println("Error opening zip file" + ioe);
        } finally {
            try {
                if (zipfile != null) {
                    zipfile.close();
                }
            } catch (IOException ioe) {
                System.out.println("Error while closing zip file" + ioe);
            }
        }
        return newDest;
    }

    public File unzipFile(File f) {
        String fileName = f.toPath().toString();
        File srcFile = new File(fileName);
        String zipPath = f.getParent();
        File temp = new File(zipPath);
        temp.mkdir();
        newDest = writeToFiles(srcFile, zipPath);
        if (newDest.getParent().contains("MACOSX")) {
            newDest = new File(newDest.getParentFile().getParentFile().getParentFile() + File.separator
                    + newDest.getParentFile().getParentFile().getParentFile().getName());
            return newDest;
        }
        return newDest.getParentFile();
    }

    public File unzipSubFile(File f) {
        String fileName = f.toPath().toString();
        File srcFile = new File(fileName);
        String zipPath = fileName.substring(0, fileName.length() - 4);
        File temp = new File(zipPath);
        temp.mkdir();
        newDest = writeToFiles(srcFile, zipPath);
        if (newDest.getParent().contains("MACOSX")) {
            newDest = new File(newDest.getParentFile().getParentFile().getParentFile() + File.separator
                    + newDest.getParentFile().getParentFile().getParentFile().getName());
            return newDest;
        }
        return newDest.getParentFile();
    }

    public Collection<File> getFolders(File loc) {
        List<File> allfolders = Arrays.asList(loc.listFiles());
        File fDest = new File(loc.getAbsolutePath());
        if (!fDest.exists()) {
            fDest.mkdirs();
        }
        for (File f : allfolders) {
            if (!f.isDirectory() && f.getName().endsWith(".zip")) {
                newDest = unzipFile(f);
                folders.add(newDest);
            } else {
                List<File> allzipfoldersinSubFile = Arrays.asList(f.listFiles());
                for (File sf : allzipfoldersinSubFile) {
                    if (!sf.isDirectory() && sf.getName().endsWith(".zip")) {
                        newDest = unzipSubFile(sf);
                        folders.add(newDest);
                    }
                }
            }
        }

        return folders;

    }

    public Collection<File> getFiles(File loc) {
        List<File> allfiles = Arrays.asList(loc.listFiles());

        for (File f : allfiles) {
            if (!f.isDirectory() && f.getName().endsWith(".pdf")) // only get the pdf files
                files.add(f);

            if (files.isEmpty()) {
                System.out.println("No PDF files found...Listing Missing Submissions");
            }
        }

        return files;
    }

    public File getCSV(File loc) {
        List<File> allfiles = Arrays.asList(loc.listFiles());

        for (File f : allfiles) {
            if (!f.isDirectory() && f.getName().endsWith(".csv"))
                this.csv = f;
        }
        if (csv == null) {
            System.out.println("No CSV file found...Terminating Program");
            System.exit(0);
        }
        return csv;
    }

}
