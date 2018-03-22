package osgi.java.client;

import org.osgi.framework.*;
import osgi.service.printer.NodePrinter;

public class ClientActivator implements BundleActivator, ServiceListener {
    private Client client;
    private BundleContext bundleContext;
    private ServiceReference serviceReference;
    private Thread t;

    @Override
    public void start(BundleContext context) throws Exception {
        client = new Client();
        this.bundleContext = context;
        serviceReference = context.getServiceReference(NodePrinter.class.getName());
        System.out.println(serviceReference);
        if (serviceReference != null) {
            NodePrinter nodePrinter = (NodePrinter) context.getService(serviceReference);
            if (nodePrinter != null) {
                client.setService(nodePrinter);
                t = new Thread(client);
                t.start();
            }
            context.addServiceListener(this, "(objectClass=" +
                    NodePrinter.class.getName() + ")");
            System.out.println("start : service-client");
        }
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {

    }

    @Override
    public void serviceChanged(ServiceEvent serviceEvent) {
        switch (serviceEvent.getType()) {
            case ServiceEvent.UNREGISTERING:
                client.stop();
                bundleContext.ungetService(
                        serviceEvent.getServiceReference());
                break;
            case ServiceEvent.REGISTERED:
                NodePrinter printer = (NodePrinter) bundleContext.getService(
                        serviceEvent.getServiceReference());
                System.out.println("SERTBHRT");
                if (printer != null) {
                    client.setService(printer);
                    client.start();
                }
                break;
        }
    }
}
