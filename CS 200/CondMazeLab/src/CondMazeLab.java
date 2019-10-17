public class CondMazeLab extends Maze
{

    public static void main(String[] args) {

        // Step 1: Run Maze Simulation
        CondMazeLab myMaze = new CondMazeLab();
    }

    public void step()
    {
        // Steps 2 and 3: Implement HERE
        if(!puss.isFacingWall())
        {
            //START WITH MUD
            if(puss.isFacingMud())
            {
                if(puss.isTipToeing())
                {
                    puss.stopTipToe();
                }
                puss.putOnBoots();
            }

            //START WITH DOG
            if(puss.isFacingDog())
            {
                if(puss.isInBoots())
                {
                    puss.takeOffBoots();
                }

                //pass dog
                puss.startTipToe();
            }

            //needs to come before gully
            puss.forward();

            //gully -- WORKS 100%
            if(puss.isFacingGully())
            {
                if(puss.isInBoots() || puss.isTipToeing())
                {
                    if(puss.isInBoots())
                    {
                        puss.takeOffBoots();
                        puss.jump();
                    }

                    if(puss.isTipToeing())
                    {
                        puss.stopTipToe();
                    }
                }

                //default
                puss.jump();
            }

        }

        if(puss.isFacingWall())
        {
            puss.right();
            if(puss.isFacingWall())
            {
                puss.right();
                //he has now spun 180 deg
                if(!puss.isFacingWall())
                {
                    puss.right();
                }
            }
        }

    }

    public CondMazeLab() { super(true); }
}
