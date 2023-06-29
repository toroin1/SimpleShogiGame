# SimpleShogiGame

A simple game of shogi using java swing

This is my first big project using java and the swing library. 

Shogi is a japanese strategy board game in the same family as chess. 

The major differences between shogi and chess is the ability to promote neary every piece into stronger one and that defeated pieces are captured by the enemy player.

The player that captured the pieces can decide to either make a move or return enemy captured piece to the board on his side.

Some illegal moves are still in the game application. For example when a pawn, lance or knight is moved on the last tile,

the piece has to promote because the pieces mentioned can only go foward and a promotion can be done only after a move, 

so once they reach the last tile if they dont promote they will have no legal moves.



There might be memory leak in GameBoard or PlayerHand or both due to circular dependency

or just badly implemented code.

I will be really glad if someone can point out the bad design decisions, bugs and other problems.

Thanks for taking the time.
