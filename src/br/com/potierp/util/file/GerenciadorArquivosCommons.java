package br.com.potierp.util.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

/**
 * Implementa o contrato GerenciadorArquivos, usando como reposit�rio a pasta temporaria.
 * Dependente da API do apache commons-io.
 */
public class GerenciadorArquivosCommons implements GerenciadorArquivos {

	/**
	 * Logger.
	 */
	private static Logger log = Logger.getLogger(GerenciadorArquivosCommons.class);

	/**
     * Tamanho do buffer para download. 100Kb.
     */
    private static final int DOWNLOAD_BUFFER_SIZE = 100 * 1024;

    /**
     * "/".
     */
    private static final String BARRA = "/";
    
	/**
	 * (non-Javadoc).
	 * @see br.metodista.service.file.GerenciadorArquivos#apagarArquivo(java.lang.String, java.lang.String)
	 */
	public boolean apagarArquivo(final String path, final String fileName)
			throws IOException {
		File f = new File(path, fileName);
		if (!f.exists())
			return false;
		//exclui o arquivo
		log.debug("Excluindo arquivo "+path+BARRA+fileName);
		FileUtils.forceDelete(f);
		return true;
	}

	/**
	 * (non-Javadoc).
	 * @see br.metodista.service.file.GerenciadorArquivos#apagarDiretorio(java.lang.String)
	 */
	public boolean apagarDiretorio(final String path) throws IOException {
		File f = new File(path);
		if (!f.exists())
			return false;
		//exclui o diretorio
		log.debug("Excluindo diretorio "+path);
		FileUtils.deleteDirectory(f);
		return true;
	}

	/**
	 * (non-Javadoc).
	 * @see br.metodista.service.file.GerenciadorArquivos#atualizarArquivo(java.lang.String, java.lang.String, byte[])
	 */
	public boolean atualizarArquivo(final String path, final String fileName, final byte[] data) throws IOException {
		return gravarArquivo(path, fileName, data);
	}

	/**
	 * (non-Javadoc).
	 * @see br.metodista.service.file.GerenciadorArquivos#downloadArquivo(java.lang.String, java.lang.String)
	 */
	public byte[] downloadArquivo(final String path, final String fileName) throws IOException {
		File f = new File(path, fileName);
		if (!f.exists())
			return null; 
		log.debug("Lendo arquivo "+path+BARRA+fileName);
		return FileUtils.readFileToByteArray(f);
	}

    /**
     * Escreve o conteudo do File no OutputStream com buffer de 100kb.
     * Detalhe importante � o <b>OutputStream � fechado</b> no final da leitura.
     * @see br.metodista.service.file.GerenciadorArquivos#downloadArquivo(java.io.File, java.io.OutputStream)
     */
	public void downloadArquivo(final File file, final OutputStream output) throws IOException {
		InputStream input = null;
		try {
			if (!file.exists())
				throw new IllegalArgumentException("");
			int readedBytes = 0;
			log.debug("Lendo com buffer arquivo "+file.getPath()+BARRA+file.getName());
	        byte[] buffer = new byte[DOWNLOAD_BUFFER_SIZE];
	        input = new FileInputStream(file);
	        while((readedBytes = input.read(buffer)) > -1) {
	        	output.write(buffer, 0, readedBytes);
	        	output.flush();
	        	Arrays.fill(buffer, (byte)0);
	        }
		} catch (IOException ioe) {
			throw ioe;
		} finally {
            try {
                if (input != null)
                    input.close();
            } catch (IOException ioex) {
                ioex.printStackTrace();
            }
            try {
                if (output != null)
                	output.close();
            } catch (IOException ioex) {
                ioex.printStackTrace();
            }
        }
	}

	/**
	 * Busca a ultima modificacao do arquivo.
	 * @see br.metodista.service.file.GerenciadorArquivos#getUltimaModificacao(java.lang.String, java.lang.String)
	 */
	public long getUltimaModificacao(final String path, final String fileName)
			throws IOException {
		File f = new File(path, fileName);
		if (!f.exists())
			return 0; 
		return f.lastModified();
	}

	/**
	 * (non-Javadoc).
	 * @see br.metodista.service.file.GerenciadorArquivos#gravarArquivo(java.lang.String, java.lang.String, byte[])
	 */
	public boolean gravarArquivo(final String path, final String fileName, final byte[] data) throws IOException {
		File f = new File(path, fileName);
		log.debug("Granvando arquivo "+path+BARRA+fileName);
		FileUtils.writeByteArrayToFile(f, data);
		return true;
	}

}