package osgi.service.printer;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;

import javax.jcr.Node;

@Component(immediate = true)

@Service
public class NodePrinterImpl  implements NodePrinter{

    @Reference
    private ResourceResolverFactory resolverFactory;


    @Activate
    public void activate(){
        System.out.println("sdvsdvetg");
    }

    @Override
    public void print(String path) {
        try{
            ResourceResolver resourceResolver = resolverFactory.getAdministrativeResourceResolver(null);
            Resource res = resourceResolver.getResource(path);
            Node node = res.adaptTo(Node.class);
            System.out.println(node.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
