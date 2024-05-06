import java.awt.Graphics2D;

public class SandPile {
    int[][] sandPile;
    Panel p;
    static int sandCount = 0;

    public SandPile(Panel p){
        this.p = p;
        sandPile = new int[p.maxCol][p.maxRow];
    }

    public void addSand(int col, int row){
        sandPile[col][row] = 1;
        sandCount++;
    }
    
    public void draw(Graphics2D g2){
        int sand = 0;
        while(sand < sandCount){

            // Start from bottom for efficiency
            for(int j = p.maxRow-1 ;j>=0;j--){
                for(int i = p.maxCol-1; i>=0;i--){
                    if(sandPile[i][j] == 1){
                        g2.fillRect(i*p.tileSize,j*p.tileSize,p.tileSize,p.tileSize);
                    }
                }
            }

            sand++;
        }
    }

    public void update(){
        int sand = 0;
        while(sand < sandCount){

            // Start from bottom for efficiency
            for(int j = p.maxRow-2 ;j>=0;j--){  // -2 because we don't want to affect bottom sand
                for(int i = p.maxCol-1; i>=0;i--){
                    if(sandPile[i][j] == 1){
                        // DESCENDS
                        if(sandPile[i][j+1] != 1){
                            sandPile[i][j+1] = 1;
                            sandPile[i][j] = 0;
                        }
                        sand++;
                    }
                }
            }
        }
    }
}

