package unc.live.d42n81.assignment1;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public int[][] boardArray = new int [8][8];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Make 64 Buttons:
        GridLayout chessBoard = (GridLayout)findViewById(R.id.grid);
        // Initialize boardArray:

    }
    public void changeBackgroundToQueen(View v){
        String id = getResources().getResourceEntryName(v.getId());
        // break id into row and column number:
        int row = Character.getNumericValue(id.charAt(1));
        int column = Character.getNumericValue(id.charAt(2));
        boolean isCarolina = isCarolina(row, column);
        boolean isDuke = !isCarolina;

        if(canPlaceQueenHere(row, column)) {
            if(isCarolina) {
                // check to see if you can place queen here:
                // Then change background to carolinaQueen if true.
                v.setBackgroundResource(R.mipmap.crownoncarolinablue);
            } else if(isDuke){
                v.setBackgroundResource(R.mipmap.crownondukebluefullsize);
            }
        } else {
            AlertDialog.Builder buildAlert = new AlertDialog.Builder(this);
            buildAlert.setMessage("You Can't Place A Queen Here.");
            final AlertDialog alert = buildAlert.create();
            alert.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    alert.dismiss();
                }
            });
            alert.show();
        }




    }

    public boolean canPlaceQueenHere(int row, int column) {
        // Implement logic to determine if you can place a queen here.
        // If you can, return true. Else, false.
        // Queen cannot be placed if there is currently a queen at (x+i,y),(x,y+i),(x-i,y),(x,y-i),
        // (x+i,y+i), (x-i,y-i).

        // (x+i,y)
        for(int i = 0; i < 100; i++){
            if(i + row > 7) {
                break;
            }
            if(boardArray[i + row][column] == 1) {
                // There is a Queen on a greater column.
                return false;
            }
        }

        // (x,y+i)
        for(int i = 0; i <100; i++) {
            if(i + column > 7) {
                break;
            }
            if(boardArray[row][i+column] == 1) {
                // There is a queen on a greater row.
                return false;
            }
        }

        // (x-i,y)
        for(int i = 0; i<100; i++){
            if(row - i < 0) {
                break;
            }
            if(boardArray[row - i][column] == 1) {
                return false;
            }
        }

        // (x, y-i)
        for(int i = 0; i < 100; i++) {
            if (column - i < 0) {
                break;
            }
            if(boardArray[row][column - i] == 1) {
                return false;
            }
        }

        // (x+i, y+i)
        for(int i = 0; i < 100; i++) {
            if(row + i > 7 || column + i > 7) {
                break;
            }
            if(boardArray[row+i][column + i] == 1) {
                return false;
            }
        }


        // (x-i,y-i)
        for(int i = 0; i <100; i++) {
            if(row-i < 0 || column -i < 0){
                break;
            }
            if(boardArray[row-i][column - i] == 1) {
                return false;
            }
        }

        boardArray[row][column] = 1;
        return true;
    }

    public boolean isCarolina(int row, int column) {
        if(row == 0 ||  row == 2 || row ==4 || row ==6) {
            if(column == 0 || column == 2 || column == 4|| column ==6){
                // isCarolina is true.
                return true;
            }
            return false;
        } else{
            // row is 1, 3, 5 , 7
            if(column==1 || column == 3|| column == 5|| column == 7){
                return true;
            }
        }
        return false;
    }

    public void restartBoard(View v) {
        String idName = "b";
        for(int i = 0; i < 8; i++) {
            for(int k = 0; k < 8; k++){
                idName = idName + i + k;
                int resID = getResources().getIdentifier(idName, "id", getPackageName());
                ImageButton myButton = (ImageButton)findViewById(resID);
                if(isCarolina(i, k)){
                    // set background to carolina square:
                    myButton.setBackgroundResource(R.mipmap.carolinabluesquare);
                } else {
                    myButton.setBackgroundResource(R.mipmap.dukebluesquare);
                }
                boardArray[i][k] = 0;
                idName = "b";
            }
        }
    }
}
