package entities;

import static utils.Constants.Directions.DOWN;
import static utils.Constants.Directions.LEFT;
import static utils.Constants.Directions.RIGHT;
import static utils.Constants.Directions.UP;
import static utils.Constants.PlayerConstants.GetSpriteAmounts;
import static utils.Constants.PlayerConstants.HIT_AIR;
import static utils.Constants.PlayerConstants.HIT_GROUND;
import static utils.Constants.PlayerConstants.IDLE;
import static utils.Constants.PlayerConstants.JUMP;
import static utils.Constants.PlayerConstants.RUN_NORMAL;
import static utils.Constants.PlayerConstants.SHOOT_STRAIGHT;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;

import main.Game;
import utils.LoadSave;

public class Player extends Entity {


    private BufferedImage[] idleAni, runNormalAni, jumpAni, hitAirAni, hitGroundAni, shootStraightAni;
    private int aniTick, aniIndex, aniSpeed = 20;
    private int playerAction = IDLE;
    private int playerDir = -1;
    private boolean moving = false, attacking = false;
    private boolean left, up, right, down, jump;
    private float playerSpeed = 2.0f;

    private float airSpeed = 0f;
    private float gravity = 0.04f * Game.SCALE;
    private float jumpSpeed = -2.25f * Game.SCALE;
    private float fallSpeedAfterCollision = 0.05f * Game.SCALE;
    private boolean inAir = false;

    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
        loadAnimations();
    }
    
    public void update() {

        updatePos();
        updateHitbox();
        updateAnimationTick();
        setAnimation();

    }

    public void render(Graphics g) {
        BufferedImage[] currentAnimation = null;

        switch (playerAction) {
            case IDLE:
                currentAnimation = idleAni;
                break;
            case RUN_NORMAL:
                currentAnimation = runNormalAni;
                break;
            case JUMP:
                currentAnimation = jumpAni;
                break;
            case HIT_AIR:
                currentAnimation = hitAirAni;
                break;
            case HIT_GROUND:
                currentAnimation = hitGroundAni;
                break;
            case SHOOT_STRAIGHT:
                currentAnimation = shootStraightAni;
            default:
                break;
        }

        if (currentAnimation == null || aniIndex >= currentAnimation.length) {
            System.err.println("Invalid animation frame: aniIndex=" + aniIndex + ", Length=" + (currentAnimation != null ? currentAnimation.length : "null"));
            return; // Prevent drawing invalid frames
        }
    
        g.drawImage(currentAnimation[aniIndex], (int) x, (int) y, null);
        drawHitbox(g);
    }

    private void updateAnimationTick() {

        aniTick++;
        if(aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;

            int maxFrames = GetSpriteAmounts(playerAction);

            if(aniIndex >= maxFrames) {
                aniIndex = 0;
                attacking = false;
                // System.out.println("Resetting aniIndex to 0 for action: " + playerAction);
            }
        }
    }

    private void setAnimation() {

        int startAni = playerAction;

        if(moving) playerAction = RUN_NORMAL;
        else playerAction = IDLE;

        if(attacking) playerAction = SHOOT_STRAIGHT ;

        if(startAni != playerAction) resetAniTick();
    }

    private void resetAniTick() {
        aniTick = 0;
        aniIndex = 0;
    }

    private void updatePos() {

        moving = false;

        if(!left && !right && !inAir) {
            return;
        }

        float xSpeed = 0;

        if(left) {

            // xSpeed -= playerSpeed;
            x -= playerSpeed;
            moving = true;
        } else if(right && !left) {
            // xSpeed += playerSpeed;
            x += playerSpeed;
            moving = true;
        }


        // if(inAir) {
            
        // } else {
        //     updateXPos(xSpeed);
        // }
        


        // if(up && !down) {
        //     y -= playerSpeed;
        //     moving = true;
        // }   else if(down && !up) {
        //     y += playerSpeed;
        //     moving = true;
        // }

    }

    // private void updateXPos(float xSpeed) {
    //     if (CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, null)) {
    //         hitbox.x += xSpeed;
    //     } else {
    //         hitbox.x = GetEntityPosNextToWall(hitbox, xSpeed);
    //     }
    // }

    private void loadAnimations() {
        // idleAni = new BufferedImage[5]; // ??? Có phải lí do chỉ load dc 5 img ko?
        // runNormalAni = new BufferedImage[16];
        // jumpAni = new BufferedImage[8];
        // hitAirAni = new BufferedImage[6];
        // hitGroundAni = new BufferedImage[6];
        // shootStraightAni = new BufferedImage[6];


        idleAni = LoadSave.loadAnimation("/res/Idle/cuphead_idle_%04d.png", 5);
        runNormalAni = LoadSave.loadAnimation("/res/Normal/cuphead_run_%04d.png", 16);
        jumpAni = LoadSave.loadAnimation("/res/Cuphead/cuphead_jump_%04d.png", 8);
        hitAirAni = LoadSave.loadAnimation("/res/Air/cuphead_hit_air_%04d.png", 6);
        hitGroundAni = LoadSave.loadAnimation("/res/Ground/cuphead_hit_%04d.png", 6);
        shootStraightAni = LoadSave.loadAnimation("/res/Shoot/Straight/cuphead_shoot_straight_%04d.png", 6);

        // for(int i = 0; i < idleAni.length; i++) {
        //     String fileName = String.format("/res/Idle/cuphead_idle_%04d.png", i+1);
            
        //     try (InputStream is = getClass().getResourceAsStream(fileName)) {
        //         if(is==null) {
        //             System.err.println("Could not load: " + fileName);
        //             continue;
        //         }
        //         idleAni[i] = ImageIO.read(is);
        //     } catch (Exception e) {
        //         System.err.println("Error loading: " + fileName);
        //         e.printStackTrace();
        //     }
        // }

        // for(int i = 0; i < runNormalAni.length; i++) {
        //     String fileName = String.format("/res/Normal/cuphead_run_%04d.png", i+1);
        //     try (InputStream is = getClass().getResourceAsStream(fileName)) {
        //         if(is==null) {
        //             System.err.println("Could not load: " + fileName);
        //             continue;
        //         }
        //         runNormalAni[i] = ImageIO.read(is);
        //     } catch (Exception e) {
        //         System.err.println("Error loading: " + fileName);
        //         e.printStackTrace();
        //     }

        //     if (runNormalAni[i] == null) {
        //         System.err.println("Failed to load frame: " + i);
        //     }
        // }

        // for(int i = 0; i < jumpAni.length; i++) {
        //     String fileName = String.format("/res/Cuphead/cuphead_jump_%04d.png", i+1);
        //     try (InputStream is = getClass().getResourceAsStream(fileName)) {
        //         if(is==null) {
        //             System.err.println("Could not load: " + fileName);
        //             continue;
        //         }
        //         jumpAni[i] = ImageIO.read(is);
        //     } catch (Exception e) {
        //         System.err.println("Error loading: " + fileName);
        //         e.printStackTrace();
        //     }
        // }

        // for(int i = 0; i < hitAirAni.length; i++) {
        //     String fileName = String.format("/res/Air/cuphead_hit_air_%04d.png", i+1);
        //     try (InputStream is = getClass().getResourceAsStream(fileName)) {
        //         if(is==null) {
        //             System.err.println("Could not load: " + fileName);
        //             continue;
        //         }
        //         hitAirAni[i] = ImageIO.read(is);
        //     } catch (Exception e) {
        //         System.err.println("Error loading: " + fileName);
        //         e.printStackTrace();
        //     }
        // }

        // for(int i = 0; i < hitGroundAni.length; i++) {
        //     String fileName = String.format("/res/Ground/cuphead_hit_%04d.png", i+1);
        //     try (InputStream is = getClass().getResourceAsStream(fileName)) {
        //         if(is==null) {
        //             System.err.println("Could not load: " + fileName);
        //             continue;
        //         }
        //         hitGroundAni[i] = ImageIO.read(is);
        //     } catch (Exception e) {
        //         System.err.println("Error loading: " + fileName);
        //         e.printStackTrace();
        //     }
        // }
        
        // for(int i = 0; i < shootStraightAni.length; i++) {
        //     String fileName = String.format("/res/Shoot/Straight/cuphead_shoot_straight_%04d.png", i+1);
        //     try (InputStream is = getClass().getResourceAsStream(fileName)) {
        //         if(is==null) {
        //             System.err.println("Could not load: " + fileName);
        //             continue;
        //         }
        //         shootStraightAni[i] = ImageIO.read(is);
        //     } catch (Exception e) {
        //         System.err.println("Error loading: " + fileName);
        //         e.printStackTrace();
        //     }
        // }
    }

    public void resetDirBooleans() {
        left = false;
        right = false;
        up = false;
        down = false;
    }

    public void setAttack(boolean attacking) {
        this.attacking = attacking;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    
    
}
