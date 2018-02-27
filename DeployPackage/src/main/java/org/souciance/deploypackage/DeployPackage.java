package org.souciance.deploypackage;

import javax.swing.*;
import java.io.*;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

/**
 * Created by moeed on 2017-10-28.
 */
public class DeployPackage {
    private String path;

    public DeployPackage(String path) {
        this.path = path;
    }

    public void create() throws IOException {
        Path packagePath = Paths.get(path);
        Path srcPath = Paths.get(path.toString().concat(File.separator).concat("src"));
        Path deployPackageFolder = createDeployPackageFolder(packagePath);
        copyPackageContent(srcPath, deployPackageFolder);
        Path codeFolder = Paths.get(deployPackageFolder.toString().concat(File.separator).concat("code"));
        cleanCodeFolder(codeFolder);
        String deployZipPackage = deployPackageFolder.getFileName().toString().concat(".zip");
        Path deployZip = Paths.get(packagePath.toString().concat(File.separator).concat("deploy").concat(File.separator).concat(deployZipPackage));
        ZipPackage.zip(deployPackageFolder.toString(), deployZip.toString(), "");
        if (deleteAppFolder(deployPackageFolder)) {
            JTextArea textarea = new JTextArea("Package created at " + deployZip.toString());
            textarea.setEditable(true);
            JOptionPane.showMessageDialog(null, textarea, "Deploy Package", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private boolean deleteAppFolder(Path app) throws IOException {
         Files.walk(app, FileVisitOption.FOLLOW_LINKS)
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .peek(System.out::println)
                .forEach(File::delete);
        return true;
    }

    private Path createDeployPackageFolder(Path path) throws IOException {
        String appName = path.getFileName().toString();
        System.out.println(appName);
        Path deployFolder = Paths.get(path.toString().concat(File.separator).concat("deploy"));
        Path appFolderWithVersion = Paths.get(deployFolder.toString().concat(File.separator).concat(appName));
        System.out.println(appFolderWithVersion.toString());
        Files.createDirectory(appFolderWithVersion);
        return appFolderWithVersion;
    }

    private void copyPackageContent(Path source, Path target) throws IOException {
        System.out.println(source.toString() + " " + target.toString());
        File sourceFolder = new File(source.toString());
        File targetFolder = new File(target.toString());
        copyDirectory(sourceFolder, targetFolder);
    }

    private void cleanCodeFolder(Path code) throws IOException {
        Files.walk(code)
                .map(Path::toFile)
                .filter(p -> p.toString().endsWith(".bar") == false)
                .forEach(File::delete);
    }

    public void copy(File sourceLocation, File targetLocation) throws IOException {
        if (sourceLocation.isDirectory()) {
            copyDirectory(sourceLocation, targetLocation);
        } else {
            copyFile(sourceLocation, targetLocation);
        }
    }

    private void copyDirectory(File source, File target) throws IOException {
        if (!target.exists()) {
            target.mkdir();
        }

        for (String f : source.list()) {
            copy(new File(source, f), new File(target, f));
        }
    }

    private void copyFile(File source, File target) throws IOException {
        try (
                InputStream in = new FileInputStream(source);
                OutputStream out = new FileOutputStream(target)
        ) {
            byte[] buf = new byte[1024];
            int length;
            while ((length = in.read(buf)) > 0) {
                out.write(buf, 0, length);
            }
        }
    }
}
