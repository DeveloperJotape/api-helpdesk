package br.com.devjoaopedro.apihelpdesk.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.web.multipart.MultipartFile;

public class UploadUtil {

    public static boolean uploadImage(MultipartFile imagem) {
        boolean sucessoUpload = false;
        if (!imagem.isEmpty()) {
            String nomeArquivo = imagem.getOriginalFilename();  
            try {
                //Criando diretorio para armazenar arquivo
                String pastaUploadImg = "../../../../../../resources/static/images/img-uploads";
                File dir = new File(pastaUploadImg);
                if (!dir.exists()) {
                    //Se não existis vai criar o diretorio
                    dir.mkdirs();    
                }
                //Criando arquivo no diretorio
                File serverFile = new File(dir.getAbsolutePath() + File.separator + nomeArquivo);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(imagem.getBytes());
                stream.close();
                System.out.println("Armazenado em: " + serverFile.getAbsolutePath());
                System.out.println("Você fez o upload do arquivo: " + nomeArquivo + " com sucesso");
            } catch (Exception e){
                System.out.println("Falha no upload do arquivo " + nomeArquivo + " => " + e.getMessage());
            }      
        } else {
            System.out.println("Falha no upload do arquivo.\nArquivo vazio!");
        }
        
        return sucessoUpload;
    }
    
}
