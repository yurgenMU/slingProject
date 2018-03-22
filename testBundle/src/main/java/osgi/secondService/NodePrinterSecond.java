package osgi.secondService;

import osgi.service.printer.NodePrinter;

public class NodePrinterSecond implements NodePrinter {
    public void print(String path) {
        System.out.println("zlp");
    }
}
