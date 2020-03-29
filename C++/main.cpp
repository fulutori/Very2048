#include <windows.h>
#include <stdio.h>
#include <iostream>
#include <ctime>
using namespace std;

class game2048 {
    private:
        int brd[4][4] = {
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
        };
        int num = 0;
        int score = 0;
        int max = 0;
        int isEnd = 0;
    public:
        void up();
        void down();
        void right();
        void left();
        void draw();
        void start();
        void add();
        int gameover();
};


// 盤面を描画する関数
void game2048::draw() {
    for (int i=0; i<4; i++) {
        for (int j=0; j<4; j++) {
            cout << "\t" << brd[i][j];
        }
        cout << "\t\n";
    }
}


// 初期配置
void game2048::start() {
    std::srand(time(NULL));
    int rand_1 = rand() % 16;
    int rand_2 = rand_1;
    while(rand_1 == rand_2) {
        rand_2 = rand() % 16;
    }
    brd[rand_1 / 4][rand_1 % 4] = 2;
    brd[rand_2 / 4][rand_2 % 4] = 2;

    printf("%d: start   Score: %d   Max: %d\n", num, score, max);
    draw();
}


// 「2」を追加
void game2048::add() {
    int cnt = 0;
    for (int i=0; i<4; i++) {
        for (int j=0; j<4; j++) {
            if (brd[i][j] == 0) {
                cnt++;
            }
        }
    }

    std::srand(time(NULL));
    int add_place = rand() % cnt + 1;
    
    cnt = 0;
    for (int i=0; i<4; i++) {
        for (int j=0; j<4; j++) {
            if (brd[i][j] == 0) {
                cnt++;
                if (cnt == add_place) {
                    brd[i][j] = 2;
                }
            }
        }
    }
}


// ゲームオーバー関数
int game2048::gameover() {
    int cnt = 0;

    // 空きマスを確認
    for (int i=0; i<4; i++) {
        for (int j=0; j<4; j++) {
            if (brd[i][j] == 0) {
                cnt++;
            }
        }
    }


    // 空きマスが無いとき
    if (cnt == 0) {

        // 縦方向に探索
        for (int i=0; i<3; i++) {
            for (int j=0; j<4; j++) {
                if (brd[i][j] == brd[i+1][j]) {
                    cnt++;
                }
            }
        }

        // 横方向に探索
        for (int i=0; i<4; i++) {
            for (int j=0; j<3; j++) {
                if (brd[i][j] == brd[i][j+1]) {
                    cnt++;
                }
            }
        }

        // 空きマスが無い状態かつ上下左右に同じ数字が無いときはゲームオーバー
        if (cnt == 0) {
            printf("\nGameOver\nNum: %d   Score: %d   Max: %d\n", num, score, max);
            return 1;
        }
    }
     
    return 0;
}


// 上方向
void game2048::up() {
    int cnt = 0;
    int move;

    // 隙間を埋める
    while(move>0) {
        move = 0;
        for (int i=0; i<3; i++) {
            for (int j=0; j<4; j++) {        
                if (brd[i][j] == 0 && brd[i+1][j] != 0) {
                    brd[i][j] = brd[i+1][j];
                    brd[i+1][j] = 0;
                    move++;
                    cnt++;
                }
            }
        }
    }

    // 同じ数字を合体
    for (int i=0; i<3; i++) {
        for (int j=0; j<4; j++) {
            if (brd[i][j] == brd[i+1][j]) {
                //printf("%d : %d\n", brd[i][j], brd[i+1][j]);
                brd[i][j] += brd[i+1][j];
                brd[i+1][j] = 0;
                score += brd[i][j];
                if (brd[i][j] > max) {
                    max = brd[i][j];
                }
                cnt++;
            }
        }
    }

    // 入力方向に寄せたとき
    if (cnt > 0) {

        // 合体に寄って生まれた隙間を埋める
        for (int i=0; i<3; i++) {
            for (int j=0; j<4; j++) {        
                if (brd[i][j] == 0 && brd[i+1][j] != 0) {
                    brd[i][j] = brd[i+1][j];
                    brd[i+1][j] = 0;
                }
            }
        }

        num++;
        printf("%d: up   Score: %d   Max: %d\n", num, score, max);
        add();
        draw();

    // タイルが入力方向に全く動かないとき
    } else {
        printf("%d: up - not move\n", num);
    }
    return;
}


// 下方向
void game2048::down() {
    int cnt = 0;
    int move;
    while(move>0) {
        move = 0;
        for (int i=3; i>0; i--) {
            for (int j=0; j<4; j++) {        
                if (brd[i][j] == 0 && brd[i-1][j] != 0) {
                    brd[i][j] = brd[i-1][j];
                    brd[i-1][j] = 0;
                    move++;
                    cnt++;
                }
            }
        }
    }

    for (int i=3; i>0; i--) {
        for (int j=0; j<4; j++) {        
            if (brd[i][j] == brd[i-1][j]) {
                brd[i][j] += brd[i-1][j];
                brd[i-1][j] = 0;
                score += brd[i][j];
                if (brd[i][j] > max) {
                    max = brd[i][j];
                }
                cnt++;
            }
        }
    }

    if (cnt > 0) {
        for (int i=3; i>0; i--) {
            for (int j=0; j<4; j++) {        
                if (brd[i][j] == 0 && brd[i-1][j] != 0) {
                    brd[i][j] = brd[i-1][j];
                    brd[i-1][j] = 0;
                }
            }
        }

        num++;
        printf("%d: down   Score: %d   Max: %d\n", num, score, max);
        add();
        draw();
    } else {
        printf("%d: down - not move\n", num);
    }
    return;
}


// 右方向
void game2048::right() {
    int cnt = 0;
    int move;
    while(move>0) {
        move = 0;
        for (int i=0; i<4; i++) {
            for (int j=3; j>0; j--) {        
                if (brd[i][j] == 0 && brd[i][j-1] != 0) {
                    brd[i][j] = brd[i][j-1];
                    brd[i][j-1] = 0;
                    move++;
                    cnt++;
                }
            }
        }
    }

    for (int i=0; i<4; i++) {
        for (int j=3; j>0; j--) {        
            if (brd[i][j] == brd[i][j-1]) {
                brd[i][j] += brd[i][j-1];
                brd[i][j-1] = 0;
                score += brd[i][j];
                if (brd[i][j] > max) {
                    max = brd[i][j];
                }
                cnt++;
            }
        }
    }

    if (cnt > 0) {    
        for (int i=0; i<4; i++) {
            for (int j=3; j>0; j--) {        
                if (brd[i][j] == 0 && brd[i][j-1] != 0) {
                    brd[i][j] = brd[i][j-1];
                    brd[i][j-1] = 0;
                }
            }
        }

        num++;
        printf("%d: right   Score: %d   Max: %d\n", num, score, max);
        add();
        draw();
    } else {
        printf("%d: right - not move\n", num);
    }
    return;
}


// 左方向
void game2048::left() {
    int cnt = 0;
    int move;
    while(move>0) {
        move = 0;
        for (int i=0; i<4; i++) {
            for (int j=0; j<3; j++) {        
                if (brd[i][j] == 0 && brd[i][j+1] != 0) {
                    brd[i][j] = brd[i][j+1];
                    brd[i][j+1] = 0;
                    move++;
                    cnt++;
                }
            }
        }
    }

    for (int i=0; i<4; i++) {
        for (int j=0; j<3; j++) {        
            if (brd[i][j] == brd[i][j+1]) {
                brd[i][j] += brd[i][j+1];
                brd[i][j+1] = 0;
                score += brd[i][j];
                if (brd[i][j] > max) {
                    max = brd[i][j];
                }
                cnt++;
            }
        }
    }
    
    if (cnt > 0) {
        for (int i=0; i<4; i++) {
            for (int j=0; j<3; j++) {        
                if (brd[i][j] == 0 && brd[i][j+1] != 0) {
                    brd[i][j] = brd[i][j+1];
                    brd[i][j+1] = 0;
                }
            }
        }

        num++;
        printf("%d: left   Score: %d   Max: %d\n", num, score, max);
        add();
        draw();
    } else {
        printf("%d: left - not move\n", num);
    }
    return;
}


int main() {
    BYTE KeyState[256];
    game2048 game;
    int u_flag=0, d_flag=0, r_flag=0, l_flag=0;
    game.start();

    while(1) {
        
        // 上方向
        if (GetKeyState(VK_UP) & 0x80 && u_flag == 0) {
            u_flag = 1;
            game.up();
        } else if ((GetKeyState(VK_UP) & 0x80) == 0 && u_flag == 1) {
            u_flag = 0;
        
        // 下方向
        } else if (GetKeyState(VK_DOWN) & 0x80 && d_flag == 0) {
            d_flag = 1;
            game.down();
        } else if ((GetKeyState(VK_DOWN) & 0x80) == 0 && d_flag == 1) {
            d_flag = 0;

        // 右方向
        } else if (GetKeyState(VK_RIGHT) & 0x80 && r_flag == 0) {
            r_flag = 1;
            game.right();
        } else if ((GetKeyState(VK_RIGHT) & 0x80) == 0 && r_flag == 1) {
            r_flag = 0;

        // 左方向
        } else if (GetKeyState(VK_LEFT) & 0x80 && l_flag == 0) {
            l_flag = 1;
            game.left();
        } else if ((GetKeyState(VK_LEFT) & 0x80) == 0 && l_flag == 1) {
            l_flag = 0;
        
        // エスケープキーで終了
        } else if (GetKeyState(VK_ESCAPE) & 0x80) {
            printf("exit\n");
            break;
        }

        // ゲームオーバーかチェック
        if (game.gameover() == 1) {
            break;
        }

        Sleep(10);
    }

    printf("\nPlease press 'Enter' key.\n\n");
    while(1) {
        if (GetKeyState(VK_RETURN) & 0x80) {
            break;
        }
        Sleep(10);
    }
        
    return 0;
}