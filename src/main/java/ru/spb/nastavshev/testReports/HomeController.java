package ru.spb.nastavshev.testReports;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.DriverManager;
import java.util.HashMap;

@Controller
public class HomeController {

    private final DataImporter dataImporter;

    public HomeController(DataImporter dataImporter) {
        this.dataImporter = dataImporter;
    }

    @GetMapping("/")
    public String index(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "home";
    }

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) throws IOException {

        dataImporter.importCsv(file);

        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/";
    }

    @PostMapping(value = "/report1")
    public String report1Action(Model model) {

        return "";
    }

    @PostMapping(value = "/report2")
    public String report2Action(Model model) {

        return "";
    }

    /*@PostMapping(value = "/report3")
    public String report3Action(Model model) {

        JasperReport jreport = JasperCompileManager
                .compileReport(getClass().getResourceAsStream("report/TestReport1.jrxmls"));

        exportReportToHtmlStream(jreport );

        return "";
    }



    public static byte[] exportReportToHtmlStream(JasperPrint jasperPrint) throws JRException
    {

        Connection conn = DriverManager.getConnection(CON_STR, "test", "test");
        HashMap hm = new HashMap();
        hm.put("Folderi", folderi);
        hm.put("punetori", pnt);
        JasperReport jreport = JasperCompileManager.compileReport(raportiPunetor);
        JasperPrint jprint = JasperFillManager.fillReport(jreport, hm, conn);
        conn.close();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        JRHtmlExporter exporter = new JRHtmlExporter();

        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);

        exporter.exportReport();

        return baos.toByteArray();
    }
*/
}
