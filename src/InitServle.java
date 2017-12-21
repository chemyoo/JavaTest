
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

/**
 * @author jianqing.liu
 * @since 2017-12-20
 */
public class InitServlet extends HttpServlet{

    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6857043657929004105L;

	@Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
        String tomcatHome = System.getProperty("catalina.home");
        CreateTomcatServiceFolder(tomcatHome);
    }

    /**
     * 映射一个web容器访问的文件夹
     * @param tomcatHome Tomcat文件根目录
     */
    protected void CreateTomcatServiceFolder(String tomcatHome)
    {
        try {
            Properties properties = PropertiesLoaderUtils.loadAllProperties("config.properties");
            String themeFolder = properties.getProperty("ThemeFolder");
            String themeUrl = properties.getProperty("ThemeUrl");
            if(themeUrl == null) {
                themeUrl = "";
            }
            String [] array = themeUrl.split("/");
            //如果未指定文件，或者是指定文件无法在本地创建，则返回
            File temp = new File(themeFolder);
            if(themeFolder == null || array.length<1 || (!temp.exists() && !temp.mkdirs()))
            {
            	return;
            }
            temp = null;
            String fileSeparator = System.getProperty("file.separator");
            String fileName = array[array.length-1];

            StringBuffer filePath = new StringBuffer(tomcatHome);
            filePath.append(fileSeparator)
                    .append("conf")
                    .append(fileSeparator)
                    .append("Catalina")
                    .append(fileSeparator)
                    .append("localhost")
                    .append(fileSeparator)
                    .append(fileName)
                    .append(".xml");

            File file = new File(filePath.toString());
            if(!file.exists()) {
                file.createNewFile();
            }
            String context = ("<?xml version='1.0' encoding='utf-8'?>" + System.getProperty("line.separator")
                    + "<Context path='${path}' docBase='${dir}' crossContext='true' reloadable='false'/>")
                    .replace("${path}","/"+fileName)
                    .replace("${dir}",themeFolder);

            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(context);
            IOUtils.closeQuietly(fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
