package vttp.day12workshop;


import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/generate")
public class imagecontroler {

    @GetMapping
    public String getImage( @RequestParam(name="name", required = false) String name,
    @RequestParam(defaultValue="1", name="count", required = true) String count, Model model) {

        model.addAttribute("name", name);
        model.addAttribute("count", Integer.parseInt(count));

        Random rand = new Random();
        int[] usedPic = new int[Integer.parseInt(count)];
        int k = 1;

        for (int i=0; i < Integer.parseInt(count); i++) {
            k=1;
            while ( k == 1) {
                int picNo = rand.nextInt(31);
                for(int j = 0 ; j < Integer.parseInt(count); j++) {
                    if ( usedPic[j] == picNo) {
                        break;
                        
                    } else if (usedPic[j] == 0) {
                        usedPic[j] = picNo;
                        k--;
                        break;
                    }
                }
            }

        }
        List<imgsrc> li = new LinkedList<>();

        for (int b = 0; b < usedPic.length ; b++){
            imgsrc img1 = new imgsrc();
            img1.setImglink("/numbers/number" + Integer.toString(usedPic[b]) + ".jpg");
            li.add(img1);
        }

        model.addAttribute("imgsrc", li);
            
        return "generate";
    }
    
}
