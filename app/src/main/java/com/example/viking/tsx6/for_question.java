package com.example.viking.tsx6;

import java.io.Serializable;

/**
 * Created by viking on 9/9/16.
 */
public class for_question implements Serializable {
    String question;
    String answer;
    String level;
    String img;
    public for_question(String question,String answer,String level,String image)
    {
        super();
        this.answer=answer;
        this.question=question;
        this.level=level;
        this.img=image;
    }
    public String getLevel() {
        return level;
    }
    public String getQuestion() {
        return question;
    }
    public String getAnswer() {
        return answer;
    }
    public String getImage() {
        return img;
    }

}

