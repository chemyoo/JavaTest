
package com.liang;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class FileChooser extends JFrame implements ActionListener{
	JButton open=null;
	public static void main(String[] args) {
		//new FileChooser().actionPerformed(null);
		String x = "0000253";
		System.err.println(x.substring(6, x.length()));
		String n1 = "G";
		String n2 = "键";
		System.err.println(gbValue(n1.charAt(0)));
		System.err.println(gbValue(n2.charAt(0)));
	}
	
	private static int gbValue(char ch) {// 将一个汉字（GB2312）转换为十进制表示。  
        String str = new String();  
        str += ch;  
        try {  
            byte[] bytes = str.getBytes("GB2312");  
            if (bytes.length < 2) {  
                return 0;  
            }  
            return (bytes[0] << 8 & 0xff00) + (bytes[1] & 0xff);  
        } catch (Exception e) {  
            return 0;  
        }  
    }  
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser jfc=new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileFilter filefilter = new FileFilter();
		filefilter.setFileExt(".txt",".jpg",".png");
		jfc.setFileFilter(filefilter);
		jfc.showDialog(new JLabel(), "请选择文件");
		File file=jfc.getSelectedFile();
		if(file==null)
		{
			System.err.println("用户取消");
			return;
		}
		if(file.isDirectory()){
			System.out.println("文件夹:"+file.getAbsolutePath());
		}else if(file.isFile()){
			System.out.println("文件:"+file.getAbsolutePath());
		}
		System.out.println(jfc.getSelectedFile().getName());
		
	}

}
class FileFilter extends javax.swing.filechooser.FileFilter
{

	private String [] fileExt;
	/**
	 * 获取 FileExt 
	 * @return the fileExt
	 */
	public String[] getFileExt()
	{
		return fileExt;
	}

	/**
	 * 设置 FileExt 
	 * @param fileExt the fileExt to set
	 */
	public void setFileExt(String... fileExt)
	{
		this.fileExt = fileExt;
	}

	@Override
	public boolean accept(File f) {
		if (f.isDirectory()) 
			return true;
		boolean flag = true;
		boolean accept = true;
		for(int i=0,szie = fileExt.length;i<szie;i++)
		{
			if(i==0)
			{
				flag = f.getName().endsWith(fileExt[i]);
				continue;
			}
			flag = flag || f.getName().endsWith(fileExt[i]);
		}
		return flag;
	}

	@Override
	public String getDescription() {
		return toString();
	}
	
	@Override
	public String toString() {
		StringBuffer r = new StringBuffer("上传文件类型 (");
		for(String ext : fileExt)
		{
			r.append("*"+ext+" , ");
		}
		if(r.lastIndexOf(", ")==r.length()-2)
			r.deleteCharAt(r.length()-2);
		r.append(")");
		return r.toString();
	}
	
}
