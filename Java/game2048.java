import java.awt.*; //色々
import java.awt.Graphics; //描画
import javax.swing.*; //フレームの取得
import java.awt.event.*; //キーイベントの取得
import java.util.Random; //ランダム関数

public class game2048 extends JFrame implements KeyListener {
	Pmodel m;
	Pview v;
	int x= 30, y= 30, keycount=0, w=0, h=0, temp=0, mark=0, move=0, twice=0,score=0;
	game2048() {
		m = new Pmodel();
		v = new Pview(m);
		Frame f = new Frame();
		f.add(v); f.pack(); f.setVisible(true);
		f.addKeyListener(this);
	}
	void start() {
		m.board[0][0] = 2;
		//m.board[0][3] = 1024;
		//m.board[3][3] = 1024;
		v.repaint();
	}

	public static void main(String args[]) {
		game2048 pu = new game2048();
		pu.start();
	}

	public void keyPressed(KeyEvent e) {
		//v.judgeGame();
		//入力されたキーを取得し、↑↓←→の場合の処理を行う
		switch(e.getKeyCode( )) {
			case KeyEvent.VK_UP :
				move=-1;	//数字移動の確認用変数
				for (int i=0 ; i<2 ; i++) {
					for (h=1 ; h<4 ; h++) {
						for (w=0 ; w<4 ; w++) {		//2~1024の数字が存在するときの処理
							if (m.board[w][h] != 0) {
								temp=0;
								temp = m.board[w][h];
								mark=h;
								if (m.board[w][mark-1] != 0) {		//[x][y]の上の座標に数字が存在するとき
									if (m.board[w][mark] == m.board[w][mark-1]) {		//[x][y]と[x][y-1]の数字が同じとき
										twice=m.board[w][mark]*2;	//同じ数字が隣り合っていたときに数字を2倍
										score += twice;
										if (twice == 2048) {	//倍にした数字が2048となったとき
											m.board[w][mark] = 0;	//[x][y]の数字を消去
											m.board[w][mark-1] = twice;	//[x][y-1]に倍にした数字を表示
											v.judgeGame();	//クリア判定
										}
										m.board[w][mark] = 0;
										m.board[w][mark-1] = twice;
										move=1;	//数字が移動したときに1にする
									}
									continue;
								}
								while(m.board[w][mark-1] == 0) {	//[x][y]より上の座標に数字が存在しない間繰り返す
									m.board[w][mark] = 0;	//[x][y]の数字を消去
									mark--;
									m.board[w][mark] = temp;	//[x][y]の数字を1つずつ上に移動
									move=1;
									if (mark == 0) {	//y=0のときは何もしない
										break;
									}
								}
							} else {
								continue;
							}
						}
					}
				}
				if (move==1) {
					keycount+=135;
					//System.out.println( keycount / 135 );
				}
				break;
			case KeyEvent.VK_RIGHT :
				move=-1;
				for (int i=0 ; i<2 ; i++) {
					for (h=0 ; h<4 ; h++) {
						for (w=2 ; w>-1 ; w--) {
							if (m.board[w][h] != 0) {
								temp=0;
								temp = m.board[w][h];
								mark=w;
								if (m.board[mark+1][h] != 0) {
									if (m.board[mark][h] == m.board[mark+1][h]) {
										twice=m.board[mark][h]*2;
										score += twice;
										if (twice == 2048) {
											m.board[mark][h] = 0;
											m.board[mark+1][h] = twice;
											v.judgeGame();
										}
										m.board[mark][h] = 0;
										m.board[mark+1][h] = twice;
										move=1;
									}
									continue;
								}
								while(m.board[mark+1][h] == 0) {
									m.board[mark][h] = 0;
									mark++;
									m.board[mark][h] = temp;
									move=1;
									if (mark == 3) {
										break;
									}
								}
							} else {
								continue;
							}
						}
					}
				}
				if (move==1) {
					keycount+=135;
					//System.out.println( keycount / 135);
				}
				break;
			case KeyEvent.VK_DOWN :
				move=-1;
				for (int i=0 ; i<2 ; i++) {
					for (h=2 ; h>-1 ; h--) {
						for (w=0 ; w<4 ; w++) {
							if (m.board[w][h] != 0) {
								temp=0;
								temp = m.board[w][h];
								mark=h;
								if (m.board[w][mark+1] != 0) {
									if (m.board[w][mark] == m.board[w][mark+1]) {
										twice=m.board[w][mark]*2;
										score += twice;
										if (twice == 2048) {
											m.board[w][mark] = 0;
	                                        m.board[w][mark+1] = twice;
											v.judgeGame();
										}
										m.board[w][mark] = 0;
                                        m.board[w][mark+1] = twice;
										move=1;
									}
									continue;
								}
								while(m.board[w][mark+1] == 0) {
									m.board[w][mark] = 0;
									mark++;
									m.board[w][mark] = temp;
									move=1;
									if (mark == 3) {
										break;
									}
								}
							} else {
								continue;
							}
						}
					}
				}
				if (move==1) {
					keycount+=135;
					//System.out.println( keycount / 135 );
				}
				break;
			case KeyEvent.VK_LEFT :
				move=-1;
				for (int i=0 ; i<2 ; i++) {
					for (h=0 ; h<4 ; h++) {
						for (w=1 ; w<4 ; w++) {
							if (m.board[w][h] != 0) {
								temp=0;
								temp = m.board[w][h];
								mark=w;
								if (m.board[mark-1][h] != 0) {
									if (m.board[mark][h] == m.board[mark-1][h]) {
										twice=m.board[mark][h]*2;
										score += twice;
										if (twice == 2048) {
											m.board[mark][h] = 0;
	                                        m.board[mark-1][h] = twice;
											v.judgeGame();
										}
										m.board[mark][h] = 0;
                                        m.board[mark-1][h] = twice;
										move=1;
									}
									continue;
								}
								while(m.board[mark-1][h] == 0) {
									m.board[mark][h] = 0;
									mark--;
									m.board[mark][h] = temp;
									move=1;
									if (mark == 0) {
										break;
									}
								}
							} else {
								continue;
							}
						}
					}
				}
				if (move==1) {
					keycount+=135;
					//System.out.println( keycount / 135 );
				}
				break;
		}
		v.judgeGame();
		v.Score(score,1);
		v.repaint();
		if ((keycount/135) % 1 == 0 && keycount != 0 && move != -1) {	//新しく「２」を追加する場所を決定
			Random rnd = new Random();
			int ran = rnd.nextInt(4);
			int ran2 = rnd.nextInt(4);
			while (m.board[ran][ran2]!=0) {	//数字が存在しない座標を生成するまでランダムに座標を生成
				ran = rnd.nextInt(4);
				ran2 = rnd.nextInt(4);
			}
			m.board[ran][ran2] = 2;	//生成した座標に「2」を表示
		}
	}
	public void keyReleased(KeyEvent e) { }
	public void keyTyped(KeyEvent e) { }
}

class Pmodel {
	int board[][];
	Pmodel() {
		board = new int[4][4];	// board の大きさは 4x4
		for(int y=0 ; y<4 ; y++) {	//board の初期化: 全要素を0にする (= 全てのマスを空にする)
			for(int x=0 ; x<4 ; x++) {
				board[x][y] = 0;
			}
		}
	}
}

class Pview extends Canvas {
	Pmodel model;
	int game=0,EndScore=0;
	public Pview(Pmodel model) {	//ウィンドウを生成
		this.model = model;
		setSize(600,600);
	}

	public void paint(Graphics gc) {
		//盤面を作成
		int i,card=0,test=0;
		//gc.setColor(new Color(184,251,255));
		gc.setColor(new Color(255,255,255));
		gc.fillRect(0,0,600,600);
		gc.setColor(new Color(0,0,0));
		for(i=0 ; i<5; i++) {
			gc.drawLine(30+i*135,30,30+i*135,570);
			gc.drawLine(30,30+i*135,570,30+i*135);
		}
		// ↓ model を参照しながら盤上に◯や×を描く
		for(int y=0 ; y<4 ; y++) {
			for(int x=0 ; x<4 ; x++) {
				//System.out.println("["+x+"]["+y+"]: "+model.board[x][y]);
				if (model.board[x][y] != 0) {
					card++;
					if (x<3) {
						if (model.board[x][y] == model.board[x+1][y]) {
							card=0;
						}
					}
					if (y<3) {
						if (model.board[x][y] == model.board[x][y+1]) {
							card=0;
						}
					}
				}
				if (model.board[x][y] == 2) { draw2(gc,x,y); }
				if (model.board[x][y] == 4) { draw4(gc,x,y); }
				if (model.board[x][y] == 8) { draw8(gc,x,y); }
				if (model.board[x][y] == 16) { draw16(gc,x,y); }
				if (model.board[x][y] == 32) { draw32(gc,x,y); }
				if (model.board[x][y] == 64) { draw64(gc,x,y); }
				if (model.board[x][y] == 128) { draw128(gc,x,y); }
				if (model.board[x][y] == 256) { draw256(gc,x,y); }
				if (model.board[x][y] == 512) { draw512(gc,x,y); }
				if (model.board[x][y] == 1024) { draw1024(gc,x,y); }
				if (model.board[x][y] == 2048) { draw2048(gc,x,y); }
				if (card == 16) {	//ゲームオーバー
					test = Score(0,0);
					Font font1 = new Font("Century", Font.PLAIN, 55);
					Font font2 = new Font("Century", Font.PLAIN, 60);
					gc.setColor(new Color(0,0,0,200));
					gc.fillRect(97,197,406,206);
					gc.setColor(new Color(200,200,200,120));
					gc.fillRect(100,200,400,200);
					gc.setColor(new Color(255,255,255));
					gc.setFont(font1);
					gc.drawString("GAMEOVER",150,280);
					gc.drawString("Score: ",120,360);
					gc.setFont(font2);
					gc.drawString(String.valueOf(test),295,360);
					try{	//5秒待機
						Thread.sleep(5000);
					}catch(InterruptedException e){}
					System.exit(1);	//ゲーム終了
				}
				if (model.board[x][y] == 1) {	//クリア
					draw2048(gc,x,y);	//「2048」を表示
					test = Score(0,0);
					Font font1 = new Font("Century", Font.PLAIN, 70);
					Font font2 = new Font("Century", Font.PLAIN, 55);
					Font font3 = new Font("Century", Font.PLAIN, 60);
					gc.setColor(new Color(0,0,0,200));
					gc.fillRect(97,197,406,206);
					gc.setColor(new Color(200,200,200,120));
					gc.fillRect(100,200,400,200);
					gc.setColor(new Color(255,255,255));
					gc.setFont(font1);
					gc.drawString("CLEAR",190,280);
					gc.setFont(font2);
					gc.drawString("Score: ",120,360);
					gc.setFont(font3);
					gc.drawString(String.valueOf(test),295,360);
					try{	//クリアの余韻に浸るために30秒待機
						Thread.sleep(30000);
					}catch(InterruptedException e){}
					System.exit(1);	//ゲーム終了
				}
			}
		}
	}

	public int judgeGame() {	//「２０４８」が出来たときにゲームを終了
		game=0;
		for (int y=0 ; y<4 ; y++) {
			for (int x=0 ; x<4 ; x++) {
				if (model.board[x][y] == 2048) {
					model.board[x][y]=1;
				}
			}
		}
		return game;
	}
	public int Score(int score,int check){
		if (check==0) {
			System.out.println("Score: "+EndScore);
		} else {
			EndScore=score;
		}
		return EndScore;
	}

	public void draw2(Graphics g, int x, int y) {	//「2」を描画
		g.setColor(new Color(0,0,0));
		g.fillRect(30+x*135,30+y*135,135,135);
		g.setColor(new Color(230,230,230));
		g.fillRect(35+x*135,35+y*135,125,125);
		g.setColor(new Color(0,0,0));
		Font font1 = new Font("Century", Font.PLAIN, 90);
    	g.setFont(font1);
		g.drawString("2",65+x*135,130+y*135);
	}
	public void draw4(Graphics g, int x, int y) {	//「4」を描画
		g.setColor(new Color(0,0,0));
		g.fillRect(30+x*135,30+y*135,135,135);
		g.setColor(new Color(245,222,199));
		g.fillRect(35+x*135,35+y*135,125,125);
		g.setColor(new Color(0,0,0));
		Font font1 = new Font("Century", Font.PLAIN, 90);
    	g.setFont(font1);
		g.drawString("4",65+x*135,130+y*135);
	}
	public void draw8(Graphics g, int x, int y) {	//「8」を描画
		g.setColor(new Color(0,0,0));
		g.fillRect(30+x*135,30+y*135,135,135);
		g.setColor(new Color(250,201,153));
		g.fillRect(35+x*135,35+y*135,125,125);
		g.setColor(new Color(0,0,0));
		Font font1 = new Font("Century", Font.PLAIN, 90);
    	g.setFont(font1);
		g.drawString("8",70+x*135,130+y*135);
	}
	public void draw16(Graphics g, int x, int y) {	//「16」を描画
		g.setColor(new Color(0,0,0));
		g.fillRect(30+x*135,30+y*135,135,135);
		g.setColor(new Color(255,160,122));
		g.fillRect(35+x*135,35+y*135,125,125);
		g.setColor(new Color(0,0,0));
		Font font1 = new Font("Century", Font.PLAIN, 80);
    	g.setFont(font1);
		g.drawString("1",50+x*135,127+y*135);
		g.drawString("6",90+x*135,127+y*135);
	}
	public void draw32(Graphics g, int x, int y) {	//「32」を描画
		g.setColor(new Color(0,0,0));
		g.fillRect(30+x*135,30+y*135,135,135);
		g.setColor(new Color(255,99,71));
		g.fillRect(35+x*135,35+y*135,125,125);
		g.setColor(new Color(0,0,0));
		Font font1 = new Font("Century", Font.PLAIN, 75);
    	g.setFont(font1);
		g.drawString("3",50+x*135,125+y*135);
		g.drawString("2",94+x*135,125+y*135);
	}
	public void draw64(Graphics g, int x, int y) {	//「64」を描画
		g.setColor(new Color(0,0,0));
		g.fillRect(30+x*135,30+y*135,135,135);
		g.setColor(new Color(255,0,0));
		g.fillRect(35+x*135,35+y*135,125,125);
		g.setColor(new Color(0,0,0));
		Font font1 = new Font("Century", Font.PLAIN, 75);
    	g.setFont(font1);
		g.drawString("6",49+x*135,125+y*135);
		g.drawString("4",97+x*135,125+y*135);
	}
	public void draw128(Graphics g, int x, int y) {	//「128」を描画
		g.setColor(new Color(0,0,0));
		g.fillRect(30+x*135,30+y*135,135,135);
		g.setColor(new Color(255,255,173));
		g.fillRect(35+x*135,35+y*135,125,125);
		g.setColor(new Color(0,0,0));
		Font font1 = new Font("Century", Font.PLAIN, 65);
    	g.setFont(font1);
		g.drawString("1",40+x*135,122+y*135);
		g.drawString("2",71+x*135,122+y*135);
		g.drawString("8",111+x*135,122+y*135);
	}
	public void draw256(Graphics g, int x, int y) {	//「256」を描画
		g.setColor(new Color(0,0,0));
		g.fillRect(30+x*135,30+y*135,135,135);
		g.setColor(new Color(255,255,100));
		g.fillRect(35+x*135,35+y*135,125,125);
		g.setColor(new Color(0,0,0));
		Font font1 = new Font("Century", Font.PLAIN, 60);
    	g.setFont(font1);
		g.drawString("2",46+x*135,120+y*135);
		g.drawString("5",80+x*135,120+y*135);
		g.drawString("6",113+x*135,120+y*135);
	}
	public void draw512(Graphics g, int x, int y) {	//「512」を描画
		g.setColor(new Color(0,0,0));
		g.fillRect(30+x*135,30+y*135,135,135);
		g.setColor(new Color(255,255,0));
		g.fillRect(35+x*135,35+y*135,125,125);
		g.setColor(new Color(0,0,0));
		Font font1 = new Font("Century", Font.PLAIN, 60);
    	g.setFont(font1);
		g.drawString("5",46+x*135,120+y*135);
		g.drawString("1",78+x*135,120+y*135);
		g.drawString("2",108+x*135,120+y*135);
	}
	public void draw1024(Graphics g, int x, int y) {	//「1024」を描画
		g.setColor(new Color(0,0,0));
		g.fillRect(30+x*135,30+y*135,135,135);
		g.setColor(new Color(102,153,255));
		g.fillRect(35+x*135,35+y*135,125,125);
		g.setColor(new Color(0,0,0));
		Font font1 = new Font("Century", Font.PLAIN, 50);
    	g.setFont(font1);
		g.drawString("1",40+x*135,115+y*135);
		g.drawString("0",63+x*135,115+y*135);
		g.drawString("2",92+x*135,115+y*135);
		g.drawString("4",122+x*135,115+y*135);
	}
	public void draw2048(Graphics g, int x, int y) {	//「2048」を描画
		g.setColor(new Color(0,0,0));
		g.fillRect(30+x*135,30+y*135,135,135);
		g.setColor(new Color(0,0,255));
		g.fillRect(35+x*135,35+y*135,125,125);
		g.setColor(new Color(0,0,0));
		Font font1 = new Font("Century", Font.PLAIN, 47);
    	g.setFont(font1);
		g.drawString("2",40+x*135,112+y*135);
		g.drawString("0",68+x*135,112+y*135);
		g.drawString("4",97+x*135,112+y*135);
		g.drawString("8",126+x*135,112+y*135);
	}
}