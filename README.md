# はじめに
ゲームの「2048」を様々なプログラミング言語で実装したものです。
  
  
## 「2048」の基本ルール
1. 盤面は4×4の16マス
1. 操作キーは↑↓→←の4つのみ
1. 登場する数字は2の倍数
1. 同じ数字のタイルが2つ隣り合うように動かすと合算されて1つになる
1. 合算されたときに出来た数字をスコアとして加算する
1. 2048を作るとクリア
1. 盤面が全て埋まり、どの方向にも動かせなくなるとゲームオーバー
  
  
## aokakes式「2048」
「2048」には基本となるルールは上記のように定められていますが、作成者によって僅かに仕様が異なることがあります。  
ここでは、基本のルールのほかにaokakesの好みを加えた以下のような仕様を元に実装しています。  
  
1. 盤面は4×4の16マス
1. 初期配置は完全ランダムで「2」が2つ
1. ↑↓→←で押された方向に全てのタイルを隙間無く寄せる
1. 同じ数字が隣り合ったときは押された方向の壁側に近い方に合算する
1. 合算によって生まれた隙間は全ての合算処理が終了した後に押された方向に寄せて消す
1. タイルを動かしたあとにランダムな位置に新たに「2」を配置する
1. 全てのタイルがその場から動かないような入力は受け付けず、新たなタイルの配置も行わない
1. 合算されたときに出来た数字をスコアとして加算する
1. 合算によって出来る数字は青天井とする(2048を作ってもゲームクリアとはならない)※理論値は131072
1. 盤面が全て埋まり、どの方向にも動かせなくなるとゲームオーバー
