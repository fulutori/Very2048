#coding: utf-8
from msvcrt import getch
import random
import os
import sys

class game2048:
    def __init__(self):
        self.brd = [[0 for j in range(4)] for i in range(4)]
        self.num = 0
        self.score = 0
        self.max = 0
        
        # タイル移動用パラメータ
        self.direction_dic = {
            "up"    :[1, 0, 0, 0, 3, 4, 1, 1],
            "down"  :[-1, 0, 3, 0, 0, 4, -1, 1],
            "right" :[0, -1, 0, 3, 4, 0, 1, -1],
            "left"  :[0, 1, 0, 0, 4, 3, 1, 1]
            }
    
    
    # タイルの初期配置を行う関数
    def start(self):
        start_tile = random.sample(range(16), k=2)
        self.brd[start_tile[0] // 4][start_tile[0] % 4] = 2
        self.brd[start_tile[1] // 4][start_tile[1] % 4] = 2
        self.draw()
    

    # ゲームオーバーを判定する関数
    def gameover(self):
        cnt = 0

        # 空きマスを探索
        for i in range(4):
            for j in range(4):
                if self.brd[i][j] == 0:
                    cnt += 1
        
        # 空きマスが無いとき
        if cnt == 0:

            # 縦方向に探索
            for i in range(3):
                for j in range(4):
                    if self.brd[i][j] == self.brd[i+1][j]:
                        cnt += 1
            
            # 横方向に探索
            for i in range(4):
                for j in range(3):
                    if self.brd[i][j] == self.brd[i][j+1]:
                        cnt += 1
            
            # 空きマスが無い状態かつ上下左右に同じ数字が無いときはゲームオーバー
            if cnt == 0:
                print("\nGameOver\nNum: {}   Score: {}   Max: {}".format(self.num, self.score, self.max))
                exit(0)

    
    # タイル追加関数
    def add(self):
        add_place = 0
        for h in self.brd:
            for w in h:
                if w == 0:
                    add_place += 1
        add_tile = random.randrange(add_place)

        cnt = 0
        for i in range(4):
            for j in range(4):
                if self.brd[i][j] == 0:
                    if cnt == add_tile:
                        self.brd[i][j] = 2
                        return
                    else:
                        cnt += 1


    # 移動関数    
    def move(self, direction):

        # 入力方向に隙間を埋める
        cnt = 0
        move = 1
        while move > 0:
            move = 0
            for i in range(self.direction_dic[direction][2], self.direction_dic[direction][4], self.direction_dic[direction][6]):
                for j in range(self.direction_dic[direction][3], self.direction_dic[direction][5], self.direction_dic[direction][7]):
                    if self.brd[i][j] == 0 and self.brd[i+self.direction_dic[direction][0]][j+self.direction_dic[direction][1]] != 0:
                        self.brd[i][j] = self.brd[i+self.direction_dic[direction][0]][j+self.direction_dic[direction][1]]
                        self.brd[i+self.direction_dic[direction][0]][j+self.direction_dic[direction][1]] = 0
                        move += 1
                        cnt += 1
        
        # 合体
        cnt = self.combine(direction, cnt)

        # 合体によって生まれた隙間を埋める
        if cnt > 0:
            for i in range(self.direction_dic[direction][2], self.direction_dic[direction][4], self.direction_dic[direction][6]):
                for j in range(self.direction_dic[direction][3], self.direction_dic[direction][5], self.direction_dic[direction][7]):
                    if self.brd[i][j] == 0 and self.brd[i+self.direction_dic[direction][0]][j+self.direction_dic[direction][1]] != 0:
                        self.brd[i][j] = self.brd[i+self.direction_dic[direction][0]][j+self.direction_dic[direction][1]]
                        self.brd[i+self.direction_dic[direction][0]][j+self.direction_dic[direction][1]] = 0
            
            self.num += 1
            print("\n{}: {}   Score: {}   Max: {}".format(self.num, direction, self.score, self.max))
            self.add()
            self.draw()
            self.gameover()
        else:
            print("{}: {} - not move".format(self.num, direction))


    # 合体用関数
    def combine(self, direction, cnt):
        for i in range(self.direction_dic[direction][2], self.direction_dic[direction][4], self.direction_dic[direction][6]):
            for j in range(self.direction_dic[direction][3], self.direction_dic[direction][5], self.direction_dic[direction][7]):
                if self.brd[i][j] == self.brd[i+self.direction_dic[direction][0]][j+self.direction_dic[direction][1]]:
                    self.brd[i][j] += self.brd[i+self.direction_dic[direction][0]][j+self.direction_dic[direction][1]]
                    self.brd[i+self.direction_dic[direction][0]][j+self.direction_dic[direction][1]] = 0

                    # Scoreを加算
                    self.score += self.brd[i][j]
                    if self.brd[i][j] > self.max:
                        self.max = self.brd[i][j]

                    cnt += 1

        return cnt

    # 盤面を描画する関数
    def draw(self):
        for i in range(4):
            print("\t{}\t{}\t{}\t{}\t".format(game.brd[i][0], game.brd[i][1], game.brd[i][2], game.brd[i][3]))


if __name__ == "__main__":
    game = game2048()
    game.start()

    while True:
        key = ord(getch())
        if key == 72:
            game.move("up")
        elif key == 80:
            game.move("down")
        elif key == 77:
            game.move("right")
        elif key == 75:
            game.move("left")
        elif key == 27:
            print("exit")
            break
        