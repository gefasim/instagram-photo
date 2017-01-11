/**
 * Created by gefasim on 10.01.2017.
 *
 */

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.newsfeed.responses.SearchExtendedResponse;
import com.vk.api.sdk.objects.wall.WallpostFull;

public class Main {

    public static void main(String[] args) {
        new Main().findInVK();
    }

    private void findInVK() {
        TransportClient transportClient = new HttpTransportClient();
        VkApiClient vk = new VkApiClient(transportClient);
        try {
            SearchExtendedResponse photo = vk.newsfeed().searchExtended().q("#rest_i").execute();

            for(WallpostFull app: photo.getItems()){
                System.out.println(app.getText() + "\n" );

                String idWall;
                if (app.getFromId() < 0) // It`s group
                    idWall = "https://vk.com/club"+(app.getFromId()*-1)+"?w=wall"+app.getFromId()+"_"+app.getId();
                else // It`s person
                    idWall = "https://vk.com/id"+app.getFromId()+"?w=wall"+app.getFromId()+"_"+app.getId();

                System.out.println(idWall);
                System.out.println("-----------------------------------------");
            }
        } catch (ApiException | ClientException e) {
            e.printStackTrace();
        }

    }


}
