package digital_image_processing;

import java.io.File;
import java.io.IOException;

public class FileHandling {

    //image file path started
    public void imgFilePath() throws IOException {
        String mainDirectory = "data/test";
        File mainFolder = new File(mainDirectory);
        String[] folderList = mainFolder.list();
        for (int i = 0; i < folderList.length; i++) {
            // sub folder
            String subDirectory = mainDirectory + "/" + folderList[i];
            File subFile = new File(subDirectory);
            String[] fileList = subFile.list();

            for (int j = 0; j < fileList.length; j++) {
                // file name
                String imgFilePath = subDirectory + "/" + fileList[j];
                Image ig = new Image();
                ig.fileOut(imgFilePath,i);
            }
        }
    }
}

