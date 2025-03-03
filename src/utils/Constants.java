package utils;

public class Constants {

    public static class Directions {
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }


    public static class PlayerConstants{
        
        public static final int RUN_NORMAL = 0;
        public static final int IDLE = 1;
        public static final int JUMP = 2;
        public static final int HIT_AIR = 3;
        public static final int HIT_GROUND = 4;
        public static final int SHOOT_STRAIGHT = 5;

        public static int GetSpriteAmounts(int player_action) {
            
            switch(player_action) {
                case RUN_NORMAL:
                    return 16;
                case IDLE:
                    return 5;
                case JUMP:
                    return 8;
                case HIT_AIR:
                    return 6;
                case HIT_GROUND:
                    return 6;
                case SHOOT_STRAIGHT:
                    return 6;
                default:
                    return 1;
            }
        }

    }
}
