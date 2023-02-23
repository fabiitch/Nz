package com.fabiitch.nz.utils.time.timers;

import com.badlogic.gdx.utils.Array;

public class CountDown {
    public float duration;
    public CountDownAction countDownAction;
    public float internalTimer;

    public CountDown(float duration, CountDownAction countDownAction) {
        this.duration = duration;
        this.countDownAction = countDownAction;
    }

    public CountDown(float duration) {
        this.duration = duration;
    }

    /**
     * return true if end
     *
     * @param dt
     * @return
     */
    public boolean update(float dt) {
        if (internalTimer < duration) {
            internalTimer += dt;
            if (internalTimer >= duration && countDownAction != null) {
                countDownAction.onEnd();
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    public void reset() {
        internalTimer = 0;
    }
}

interface CountDownAction {
    void onEnd();
}

class MultiCountDownAction implements CountDownAction {
    public Array<CountDownAction> actions = new Array<>();

    @Override
    public void onEnd() {
        for (CountDownAction action : actions) {
            action.onEnd();
        }
    }
}
