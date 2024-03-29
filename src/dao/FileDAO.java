package dao;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import dao.FileDAO.FileName;

public class FileDAO {

	private String txtPath = "src/files";
	private Charset charSet = StandardCharsets.UTF_8;
	enum FileName {
		BOARD("board.txt"), MEMBER("member.txt"), ITEM("item.txt"), CART("cart.txt");
		private String name;
		FileName(String name) {
			this.name = name;
		}
		public String getName() {
			return name;
		}
		
	
	}

	private FileDAO() {}

	private static FileDAO instance = new FileDAO();

	static public FileDAO getInstance() {
		return instance;
	}
	
	private void createFile(FileName name) {
		Path path = Paths.get("src/files/" + name.getName());
		try {
			Files.createFile(path);
		} catch (IOException e) {
			//System.out.println("파일이 이미 있음");
		}
	}

	private void init() {

		createFile(FileName.BOARD);
		createFile(FileName.MEMBER);
		createFile(FileName.ITEM);
		createFile(FileName.CART);

	}
	

	public void saveAllAsFile() {
		saveFile(FileName.BOARD,BoardDAO.getInstance().listToFile());
		saveFile(FileName.MEMBER,MemberDAO.getInstance().listToFile());
		saveFile(FileName.ITEM, ItemDAO.getInstance().listToFile());
		saveFile(FileName.CART,CartDAO.getInstance().listToFile());
		System.out.println("== 데이터 저장 완료 == ");
	}

	public void loadAllFiles() {

		
		BoardDAO.getInstance().loadData(loadFile(FileName.BOARD));
		MemberDAO.getInstance().loadData(loadFile(FileName.MEMBER));
		ItemDAO.getInstance().loadData(loadFile(FileName.ITEM));
		CartDAO.getInstance().loadData(loadFile(FileName.CART));
		
		System.out.println("== 데이터 로드 완료 == ");
		
		
	}


	private void saveFile(FileName name, String data) {
		Path path = Paths.get("src/files/" + name.getName());
		try (
				FileOutputStream fos = new FileOutputStream(path.toString());
				OutputStreamWriter ow = new OutputStreamWriter(fos, charSet);
				BufferedWriter bw = new BufferedWriter(ow);)
		{
			bw.write(data);

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	private String loadFile(FileName name) {
		
		Path path = Paths.get(txtPath , name.getName());
		StringBuilder data = new StringBuilder();
		
		try( FileInputStream fis = new FileInputStream(path.toString());
			 InputStreamReader ir = new InputStreamReader(fis, charSet);
			 BufferedReader br = new BufferedReader(ir);
				){
			String line;
			while((line= br.readLine()) != null) {
				data.append(line);
				data.append("\n");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return data.toString().substring(0, data.toString().length()-1);
	}
	

}
