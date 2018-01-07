
package cms;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;


public class set {
    
    public static void informationmessage(String title,String msg){
        
        
         Notifications n = Notifications.create()
                        .title(title)
                        .text(msg)
                        .graphic(null)
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.TOP_RIGHT);
                    
                 n.darkStyle();
                n.showInformation();
    }
    
     public static void warningmessage(String title,String msg){
        
        
         Notifications n = Notifications.create()
                        .title(title)
                        .text(msg)
                        .graphic(null)
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.TOP_RIGHT);
                    
                 n.darkStyle();
                n.showWarning();
    }
     
     public static boolean confirm(String msg ){
         Alert al = new Alert(Alert.AlertType.WARNING,msg,ButtonType.YES ,ButtonType.NO);
        al.showAndWait();
        if(al.getResult() == ButtonType.YES){
            return true;
        }
        return false;
     }
    
}
