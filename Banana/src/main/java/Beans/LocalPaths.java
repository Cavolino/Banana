package Beans;

import java.io.File;

public class LocalPaths {
	public static String basePath = getBasePath() + "/..";
	public static String pathEventi = basePath + "/eventi.json";
	public static String pathEventiInteresse = basePath + "/eventiInteresse.json";
	public static String pathLocali = basePath + "/locali.json";
	public static String pathVerificaOrganizzatore = basePath + "/verificaOrganizzatore.json";
	
	private static String getBasePath() {
        String basePath = "";
        try {
        	basePath = new File(LocalPaths.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().getPath();
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return basePath;
	}
}
