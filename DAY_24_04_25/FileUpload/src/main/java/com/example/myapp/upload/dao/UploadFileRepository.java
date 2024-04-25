package com.example.myapp.upload.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.myapp.upload.model.UploadFile;
import com.example.myapp.upload.model.UploadFileDto;

@Repository
public class UploadFileRepository implements IUploadFileRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public int getMaxFileId() {
		String sql = "SELECT NVL(MAX(file_id),0) FROM upload_file";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	@Override
	public void uploadFile(UploadFile file) {
		String sql = "INSERT INTO upload_file "
				+ " (file_id, category_name, file_name, file_size, "
				+ " file_content_type, file_upload_date, file_data) "
				+ " VALUES (?, ?, ?, ?, ?, SYSTIMESTAMP, ?)";
		jdbcTemplate.update(sql,
				file.getFileId(),
				file.getCategoryName(),
				file.getFileName(),
				file.getFileSize(),
				file.getFileContentType(),
				file.getFileData());
	}

	@Override
	public List<UploadFile> getFileList(String categoryName) {
		String sql = "SELECT file_id, category_name, file_name, file_size, "
				+ " file_content_type, file_upload_date "
				+ " FROM upload_file "
				+ " WHERE category_name=? "
				+ " ORDER BY file_upload_date DESC ";
		return jdbcTemplate.query(sql, new RowMapper<UploadFile>() {
				@Override
				public UploadFile mapRow(ResultSet rs, int rowNum) throws 
	SQLException {
					UploadFile file = new UploadFile();
					file.setFileId(rs.getInt("file_id"));
					file.setCategoryName(rs.getString("category_name"));
					file.setFileName(rs.getString("file_name"));
					file.setFileSize(rs.getLong("file_size"));
					file.setFileContentType(rs.getString("file_content_type"));
					file.setFileUploadDate(rs.getTimestamp("file_upload_date"));
					return file;
			}
		}, categoryName);
	}

	private class UploadFileMapper implements RowMapper<UploadFile> {
		@Override
		public UploadFile mapRow(ResultSet rs, int rowNum) throws SQLException {
			UploadFile file = new UploadFile();
			file.setFileId(rs.getInt("file_id"));
			file.setCategoryName(rs.getString("category_name"));
			file.setFileName(rs.getString("file_name"));
			file.setFileSize(rs.getLong("file_size"));
			file.setFileContentType(rs.getString("file_content_type"));
			file.setFileUploadDate(rs.getTimestamp("file_upload_date"));
			try {
				file.setFileData(rs.getBytes("file_data"));
			} catch (Exception e) {
				// nothing : 아무것도 안함
			}
			return file;
		}
	}
	
	@Override
	public List<UploadFile> getAllFileList() {
		String sql = "SELECT file_id, category_name, file_name, file_size, "
				+ " file_content_type, file_upload_date "
				+ " FROM upload_file "
				+ " ORDER BY file_upload_date DESC ";
		return jdbcTemplate.query(sql, new UploadFileMapper());
	}

	@Override
	public List<UploadFile> getImageList(String categoryName) {
		String sql = "SELECT file_id, category_name, file_name, file_size, "
				+ " file_content_type, file_upload_date, file_data "
				+ " FROM upload_file "
				+ " WHERE category_name=? "
				+ " ORDER BY file_upload_date DESC ";
		return jdbcTemplate.query(sql, new RowMapper<UploadFile>() {
			@Override
			public UploadFile mapRow(ResultSet rs, int rowNum) throws
SQLException {
				UploadFile file = new UploadFile();
				file.setFileId(rs.getInt("file_id"));
				file.setCategoryName(rs.getString("category_name"));
				file.setFileName(rs.getString("file_name"));
				file.setFileSize(rs.getLong("file_size"));
				file.setFileContentType(rs.getString("file_content_type"));
				file.setFileData(rs.getBytes("file_data"));
				file.setFileUploadDate(rs.getTimestamp("file_upload_date"));
				return file;
			}
		}, categoryName);
	}

	@Override
	public UploadFile getFile(int fileId) {
		String sql = "SELECT file_id, category_name, file_name, file_size, "
				+ " file_content_type, file_upload_date, file_data "
				+ " FROM upload_file "
				+ " WHERE file_id=?";
		return jdbcTemplate.queryForObject(sql, new UploadFileMapper(), fileId);
	}

	@Override
	public String getCategoryName(int fileId) {
		String sql = "SELECT category_name FROM upload_file WHERE file_id=?";
		return jdbcTemplate.queryForObject(sql, String.class, fileId);
	}

	@Override
	public void updateCategory(HashMap<String, Object> map) {
		String sql = "UPDATE upload_file SET category_name=? WHERE file_id=?";
		jdbcTemplate.update(sql, map.get("categoryName"), map.get("fileId"));
	}

	@Override
	public void deleteFile(int fileId) {
		String sql = "DELETE FROM upload_file WHERE file_id=?";
		jdbcTemplate.update(sql, fileId);
	}

	// --- 파일 시스템을 사용한 파일 업로드----------------------------
	@Override
	public int getMaxFileId2() {
		String sql = "SELECT NVL(MAX(file_id),0) FROM upload_file2";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	
	@Override
	public void uploadFile2(UploadFileDto file) {
		String sql = "INSERT INTO upload_file2 "
				+ " (file_id, category_name, file_name, uuid_file_name, "
				+ " file_size, file_content_type, file_upload_date) "
				+ " VALUES (?, ?, ?, ?, ?, ?, SYSTIMESTAMP)";
		jdbcTemplate.update(sql, 
				file.getFileId(),
				file.getCategoryName(),
				file.getFileName(),
				file.getUuidFileName(),
				file.getFileSize(),
				file.getFileContentType()
		);
	}
	
	@Override
	public List<UploadFileDto> getAllFileList2() {
		String sql = "SELECT file_id, category_name, file_name, uuid_file_name, "
				+ " file_size, file_content_type, file_upload_date "
				+ " FROM upload_file2 "
				+ " ORDER BY file_upload_date DESC ";
		return jdbcTemplate.query(sql, new RowMapper<UploadFileDto>() {
			@Override
			public UploadFileDto mapRow(ResultSet rs, int rowNum) throws SQLException {
				UploadFileDto file = new UploadFileDto();
				file.setFileId(rs.getInt("file_id"));
				file.setCategoryName(rs.getString("category_name"));
				file.setFileName(rs.getString("file_name"));
				file.setUuidFileName(rs.getString("uuid_file_name"));
				file.setFileSize(rs.getLong("file_size"));
				file.setFileContentType(rs.getString("file_content_type"));
				file.setFileUploadDate(rs.getTimestamp("file_upload_date"));
				return file;
			}
		});
	}
	
	
	@Override
	public UploadFileDto getFile2(int fileId) {
		String sql = "SELECT file_id, category_name, file_name, uuid_file_name, "
				+ " file_size, file_content_type, file_upload_date "
				+ " FROM upload_file2 "
				+ " WHERE file_id=?";
		return jdbcTemplate.queryForObject(sql, new RowMapper<UploadFileDto>() {
			@Override
			public UploadFileDto mapRow(ResultSet rs, int rowNum) throws SQLException {
				UploadFileDto file = new UploadFileDto();
				file.setFileId(rs.getInt("file_id"));
				file.setCategoryName(rs.getString("category_name"));
				file.setFileName(rs.getString("file_name"));
				file.setUuidFileName(rs.getString("uuid_file_name"));
				file.setFileSize(rs.getLong("file_size"));
				file.setFileContentType(rs.getString("file_content_type"));
				file.setFileUploadDate(rs.getTimestamp("file_upload_date"));
				return file;
			}
		}, fileId);
	}
	
	@Override
	public String getUuidFileName(int fileId) {
		String sql = "SELECT uuid_file_name FROM upload_file2 WHERE file_id=?";
		return jdbcTemplate.queryForObject(sql, String.class, fileId);
	}
	
	@Override
	public void deleteFile2(int fileId) {
		String sql = "DELETE FROM upload_file2 WHERE file_id=?";
		jdbcTemplate.update(sql, fileId);
	}
}
