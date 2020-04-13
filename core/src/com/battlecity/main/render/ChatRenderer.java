package com.battlecity.main.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.*;
import com.battlecity.main.assets.AssetsGame;
import static com.battlecity.main.render.Batch.BATCH;



/**
 *
 * @author JH62
 */
public class ChatRenderer
{

    public static String chatText = "";

    private static final Color CHAT_COLOR = new Color(0, 0, 170, 0.35f);

    private static final int CHAT_POSX = 1;
    private static final int CHAT_POSY = 1;
    private static final int MAX_CHAT_TEXT = 52;

    private static final int MAX_CHAT_PERSISTANCE = 500;
    private static int lastRenderCall = 0;

    public static void renderChatInput() {

        float width = AssetsGame.manager().chatFont.getSpaceWidth();
        float height = AssetsGame.manager().chatFont.getCapHeight();

        drawChatBox(CHAT_POSX - 1, CHAT_POSY - 1, (width * MAX_CHAT_TEXT) +
                                                  width, height * 2f);

        if(!chatText.isEmpty()) {
            AssetsGame.manager().chatFont.getData().setScale(0.21f);
            AssetsGame.manager().chatFont.setColor(Color.WHITE);
            AssetsGame.manager().chatFont.draw(Batch.BATCH, chatText, CHAT_POSX,
                                               CHAT_POSY + height);
        }

    }

    public static boolean renderChatOutput(String outputText) {
        if(lastRenderCall >= MAX_CHAT_PERSISTANCE) {
            onChatSent();
            return true;
        }

        float width = AssetsGame.manager().chatFont.getSpaceWidth();
        float height = AssetsGame.manager().chatFont.getCapHeight();
        drawChatBox(CHAT_POSX,
                    CHAT_POSY, (width * MAX_CHAT_TEXT) + width, height * 2f);

        lastRenderCall++;

        AssetsGame.manager().chatFont.getData().setScale(0.21f);
        AssetsGame.manager().chatFont.setColor(Color.WHITE);
        AssetsGame.manager().chatFont.draw(Batch.BATCH, outputText, CHAT_POSX,
                                           CHAT_POSY + (AssetsGame.manager().chatFont.getCapHeight() * 3f));
        return false;
    }

    public static void appendToChat(char character) {
        if(chatText.length() < MAX_CHAT_TEXT) {
            chatText += character;
            if(!Character.isSpaceChar(character)) {
                chatText = chatText.trim();
            }
        }
    }

    /**
     * Resets the time elapsed since the last draw update and empties the string buffer.
     */
    public static void onChatSent() {
        chatText = "";
        lastRenderCall = 0;
    }

    public static void deleteCharacter() {
        if(!chatText.isEmpty()) {
            chatText = chatText.substring(0, chatText.length() - 1);
        }
    }

    private static void drawChatBox(float x, float y, float width, float height) {
        BATCH.end();
        Batch.SPRENDERER.setProjectionMatrix(BATCH.getProjectionMatrix());
        /**
         * Box bounds
         */
        Batch.SPRENDERER.begin(ShapeRenderer.ShapeType.Line);
        Batch.SPRENDERER.setColor(Color.DARK_GRAY);
        Batch.SPRENDERER.box(x, y, 1, width, height, 1);
        Batch.SPRENDERER.end();

        /**
         * Box
         */
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Batch.SPRENDERER.begin(ShapeRenderer.ShapeType.Filled);
        Batch.SPRENDERER.setColor(CHAT_COLOR);
        Batch.SPRENDERER.box(x, y, 0, width, height, 0);
        Batch.SPRENDERER.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);

        BATCH.begin();
    }

}
