import java.io.File;

public enum Extention {
    PNG,
    JPG,
    NONE;

    public static Extention getExtentionFromFile(File file){
        for (Extention ext : Extention.values()){
            if (file.getName().endsWith(ext.toString().toLowerCase())) return ext;

        }

        return NONE;

    }
}
