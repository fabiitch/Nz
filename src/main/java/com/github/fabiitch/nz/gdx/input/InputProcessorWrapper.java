package com.github.fabiitch.nz.gdx.input;

import com.badlogic.gdx.InputProcessor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class InputProcessorWrapper implements InputProcessor {
    private final InputProcessor processor;

    @Override
    public boolean keyDown(int keycode) {
        return processor.keyDown(keycode);
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return processor.mouseMoved(screenX, screenY);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return processor.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean keyUp(int keycode) {
        return processor.keyUp(keycode);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return processor.touchUp(screenX, screenY, pointer, button);
    }

    @Override
    public boolean keyTyped(char character) {
        return processor.keyTyped(character);
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return processor.touchCancelled(screenX, screenY, pointer, button);
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return processor.scrolled(amountX, amountY);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return processor.touchDragged(screenX, screenY, pointer);
    }
}
