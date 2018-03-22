package osgi.secondService;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import osgi.service.printer.NodePrinter;

public class NodePrinterActivator implements BundleActivator {

    @Override
    public void start(BundleContext bundleContext) throws Exception {
        bundleContext.registerService(
                NodePrinter.class.getName(), new NodePrinterSecond(), null);
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {

    }
}
