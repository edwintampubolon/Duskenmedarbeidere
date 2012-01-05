package no.dusken.medarbeidere.control;

import no.dusken.common.util.ImageReader;
import no.dusken.common.util.impl.ImageReaderImpl;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;

@Controller
public class ImageController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Value("#{ dataDir }/image")
    private File imagefolder;

    @Value("#{ dataDir }/image/cache")
    private File imageCachefolder;

    ImageReader imageReader = new ImageReaderImpl();

    @RequestMapping("/image")
    public void handleImage(@RequestParam String path, @RequestParam(required = false, defaultValue = "0") Integer height, @RequestParam(required = false, defaultValue = "0") Integer width, HttpServletResponse response) throws IOException {
        File image = new File(imagefolder, path);
        if (!image.exists() || !image.canRead()) {
            log.warn("Error reading: {}", image);
            try {
                response.sendError(404);
            } catch (IOException e) {
                log.error("Could not write to response", e);
            }
            return;
        }

        File cache = getCacheFile(height, width);
        if (cache == null) return;

        image = getOrCreateCachedFile(path, height, width, cache);

        response.setContentType("image/jpeg");
        response.setHeader("Cache-Control", "public, max-age=92505600");
        response.setContentLength((int) image.length());

        OutputStream outputStream = response.getOutputStream();
        InputStream inputStream = new FileInputStream(image);
        IOUtils.copy(inputStream, outputStream);
    }

    private File getOrCreateCachedFile(String path, Integer height, Integer width, File cache) throws IOException {
        File cachedimage = new File(cache, path);
        if(!cachedimage.exists()){
            File image = new File(imagefolder, path);
            BufferedImage bufferedImage = imageReader.readImage(image, height, width);
            cachedimage.mkdirs();
            ImageIO.write(bufferedImage, "jpeg", cachedimage);
        }
        return cachedimage;
    }

    private File getCacheFile(Integer height, Integer width) {
        File cache = new File(imageCachefolder, height + "x" + width);
        if(!cache.exists()){
            if (!cache.mkdirs()){
                log.error("Could not create folder {}", cache.getAbsolutePath() );
                cache = null;
            }
        }
        return cache;
    }
}
