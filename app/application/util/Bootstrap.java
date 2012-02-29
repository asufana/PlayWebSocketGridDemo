package application.util;

import domain.model.story.*;
import play.jobs.*;

@OnApplicationStart
public class Bootstrap extends Job {
    public void doJob() {
        new Story("利用者はストーリーの一覧を参照できる", false).save();
        new Story("利用者はストーリーを着手中とバックログと分類できる", false).save();
        new Story("利用者はストーリーの順序を変更することができる", false).save();
    }
}

