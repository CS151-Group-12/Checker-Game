# Checker Game
This Checker Game is built using MVC Model, and Java 8.

# Getting Started
Clone this repository

```bash
git clone git@github.com:CS151-Group-12/Checker-Game.git
```

# Project Structure

This project is divided into 3 folders:
1. Model: Handling all the logic and rules of the game
2. View: Update the GUI based on the changes in the model
3. Controller: Signal the Model/View what changes so it can update constantly the Model if View change, or vice versa.

```
Controller
  ├── Controller      - Update the View or Model when there is an action from the user.
  ├── Main            - Start the Checker Game, initialize the multithreading 
  ├── Valve           — BlockingQueue that used to listen to both Model/View so it can trigger the action constantly
  ├── ValveResponse   - 
Message
  ├── Message         — Data send from/to Model/View
  ├── NewGameMessage  -
Model
  ├── Model           — Initialize all the Models && main communication between Model and Controller
  ├── Moves           - Contain the piece and ist previous and the Current position
  ├── Piece           — 
  ├── PieceType       - Enum: type of pieces
  ├── Player          - store all actions and info of the player
  ├── Tile            - contain the colors and piece 
View
  ├── BoardPanel      - setup the board game and starting state of the board
  ├── CheckersPeice   - Shape of the piece
  ├── GameInfo        - contain all the information of current state && main communication between Model and View
  ├── HistoryPanel    - store all the moves 
  ├── MainFrame       - setup the frame and intialize all the panels
  ├── View            - serve as the main communication between View and Controller
```

# Authors

**Calvin Nguyen** - [NivlaCuong](https://github.com/NivlaCuong)

**Hemant Thobbi** - [hemant-thobbi](https://github.com/hemant-thobbi)

**Justin Lo** - [justinlo4377](https://github.com/justinlo4377)

## License

Use of this source code is governed by an MIT-style license.
