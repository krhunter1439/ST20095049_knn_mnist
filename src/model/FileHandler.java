package model;

import java.io.*;

public abstract class FileHandler {
	
	File fp;
	String sourceName;
	String destName;
	int file_size;
	int is_directory;
	String file_type;
	
	public FileHandler() {}
	public FileHandler(String source_name) {
		this.sourceName = source_name;
		this.fp = new File(this.sourceName);
	}
	
	public FileHandler(String source_file, String dest_file) {
		this.sourceName = source_file;
		this.destName = dest_file;
		this.fp = new File(this.sourceName);
	}
	
	public abstract void readFile() throws IOException;
	
	public abstract void writeFile(String file_name) throws IOException;
	
	public abstract File getFile();
	
	public abstract void setFile(File file);
	
	public abstract String getSourceName();
	
	public abstract void setSourceName(String source_Name);
	
	public abstract String getDestinationName();
	
	public abstract void setDestinationName(String dest_Name);
	
	public abstract int getFileSize();
	
	public abstract void setFileSize(int fileSize);
	
	public abstract int getIsDirectory();
	
	public abstract void setIsDirectory(int DirectoryValue);
	
	public abstract String getFileType();
	
	public abstract void setFileType(String fileType);
}
