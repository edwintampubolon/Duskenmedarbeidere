package no.dusken.medarbeidere.control;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
public class ImageController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Value("#{ dataDir }/image")
    private File imagefolder;

    @RequestMapping("/image/{path}")
    public void handleImage(@PathVariable String path, HttpServletResponse response) throws IOException {
        File image = new File(imagefolder, path.replace("_", "/"));
        if (!image.exists() || !image.canRead()) {
            log.warn("Error reading: {}", image);
            try {
                response.sendError(404);
            } catch (IOException e) {
                log.error("Could not write to response", e);
            }
            return;
        }
        response.setContentType("image/jpeg");
        response.setHeader("Cache-Control", "public, max-age=92505600");
        response.setContentLength((int) image.length());

        OutputStream outputStream = response.getOutputStream();
        InputStream inputStream = new FileInputStream(image);
        IOUtils.copy(inputStream, outputStream);
    }
}
