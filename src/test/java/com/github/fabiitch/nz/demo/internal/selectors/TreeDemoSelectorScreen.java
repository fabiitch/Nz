package com.github.fabiitch.nz.demo.internal.selectors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.github.fabiitch.nz.demo.internal.NzDemoApplication;
import com.github.fabiitch.nz.gdx.scene2D.SimpleClickListener;
import com.github.fabiitch.nz.gdx.scene2D.nz.NzStage;
import com.github.fabiitch.nz.gdx.scene2D.nz.utils.StagePlacementUtils;

public class TreeDemoSelectorScreen extends ScreenAdapter {

    private NzDemoApplication demoApplication;
    public NzStage stage;
    private final Skin skin;
    public DemoScreenTree tree;

    public TreeDemoSelectorScreen(NzDemoApplication demoApplication, DemoScreenTree tree) {
        this.demoApplication = demoApplication;
        this.tree = tree;

        this.skin = new Skin(Gdx.files.internal("skins/ui/uiskin.json"));
        this.stage = new NzStage();
        Gdx.input.setInputProcessor(stage);
        createTable();
    }

    public void onGroupClicked(DemoScreenTree tree) {
        demoApplication.setSelectorScreen(tree);
    }

    public void onScreenClicked(DemoScreenTree.Leaf leafScreen) {
        demoApplication.setDemoScreen(leafScreen.getScreenClass());
    }

    void createTable() {
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
        table.row().size(stage.getHeight() / 8);

        //groupLabel
        Label nameLabel = new Label("Group : " + tree.getGroupName(), skin);
        nameLabel.setColor(Color.RED);
        nameLabel.setFontScale(2);
        StagePlacementUtils.placeCenterX(nameLabel, Gdx.graphics.getWidth() / 2);
        StagePlacementUtils.placeCenterY(nameLabel, Gdx.graphics.getHeight() - 50);
        stage.addActor(nameLabel);

        int i = 0;

        for (DemoScreenTree child : tree.getChildrens()) {
            TextButton textButton = new TextButton(child.getGroupName(), skin);
            table.add(textButton).width(stage.getWidth() / 4);
            textButton.setColor(Color.RED);
            i++;
            if (i % 4 == 0) {
                table.row().size(stage.getHeight() / 8);
            }
            textButton.addListener((SimpleClickListener) () -> {
                onGroupClicked(child);
            });
        }

        table.row().size(stage.getHeight() / 16);
        table.add();
        table.row().size(stage.getHeight() / 8);
        i = 0;
        for (DemoScreenTree.Leaf leaf : tree.getLeafs()) {
            TextButton textButton = new TextButton(leaf.getName(), skin);
            table.add(textButton).width(stage.getWidth() / 4);
            textButton.setColor(Color.GREEN);
            i++;
            if (i % 4 == 0) {
                table.row().size(stage.getHeight() / 8);
            }
            textButton.addListener((SimpleClickListener) () -> {
                onScreenClicked(leaf);
            });
        }
    }

    @Override
    public void resize(int width, int height) {
        stage.resize(width, height);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLUE);
        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
