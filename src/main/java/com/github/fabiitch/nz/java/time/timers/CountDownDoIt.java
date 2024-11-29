package com.github.fabiitch.nz.java.time.timers;

import com.github.fabiitch.nz.java.function.DoIt;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountDownDoIt extends CountDown {
    private DoIt doIt;

    public CountDownDoIt(float duration, DoIt doIt) {
        super(duration);
        this.doIt = doIt;
    }

    @Override
    public boolean update(float dt) {
        if (!finish())
            return super.update(dt);
        else {
            boolean finish = super.update(dt);
            if (finish)
                doIt.doIt();
            return finish;
        }
    }
}

