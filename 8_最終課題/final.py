import tkinter as tk
from tkinter import messagebox
import sqlite3
from datetime import datetime


# SQLiteデータベースに接続
with sqlite3.connect('todo.db') as conn:

    # カーソルを取得
    cursor = conn.cursor()

    # ToDoテーブルを作成
    cursor.execute('''
        CREATE TABLE IF NOT EXISTS todos (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            task TEXT NOT NULL,
            completed BOOLEAN NOT NULL,
            due_date DATE,
            due_time TIME,
            end_time TIME
        )
    ''')

    # データベースに変更を保存
    conn.commit()
# withステートメントを抜ける時点で、データベース接続は自動的にクローズされる。


def submit_form():
    try:
        task = entry_task.get()
        duedate = entry_due_date.get()
        duetime = entry_due_time.get()
        endtime = entry_end_time.get()

        # フォーマットが正しいか確認
        detatime.strptime(duedate, '%Y-%m-%d')
        datetime.strptime(duetime, '%H:%M')
        datetime.strptime(endtime, '%H:%M')

        # ToDoをデータベースに追加
        add_todo(task, duedate, duetime, endtime)

        messagebox.showinfo("フォームの内容", f"タスク: {task}, 日付: {duedate} が送信されました。")

        # フォーム送信後にデータを再取得して表示
        display_todos()

    except ValueError:
        messagebox.showerror("エラー", "日付または時間の形式が正しくありません。")

def add_todo(task, due_date=None, due_time=None, end_time=None):
    # 日付と時間を文字列からdatetimeオブジェクトに変換
    # due_date = datetime.strptime(f'{due_date} {due_time}', '%Y-%m-%d %H:%M:%S') if due_date else None
    due_date = datetime.strptime(due_date, '%Y-%m-%d') if due_date else None
    due_time = datetime.strptime(due_time, '%H:%M') if due_time else None
    end_time = datetime.strptime(end_time, '%H:%M') if end_time else None

    cursor.execute('INSERT INTO todos (task, completed, due_date, due_time, end_time) VALUES (?, ?, ?, ?, ?)',
     (task, False, due_date, due_time, end_time))
    conn.commit()

def display_todos():
    # 日付と時間でソートしてToDoリストを取得
    cursor.execute("SELECT task, strftime('%Y-%m-%d', due_date), strftime('%H:%M', due_time), strftime('%H:%M', end_time) FROM todos ORDER BY due_date, due_time")
    todos = cursor.fetchall()

    # リストボックスをクリア
    listbox.delete(0, tk.END)

    # ToDoをリストボックスに表示
    for todo in todos:
        listbox.insert(tk.END, f"タスク: {todo[0]}, 日付: {todo[1]}, 開始時間: {todo[2]}, 終了時間: {todo[3]}")

# Tkinterウィンドウの作成
root = tk.Tk()
root.title("タスク入力画面")

# ラベルとエントリー(タスク)
label_task = tk.Label(root, text="タスク")
label_task.pack(padx=10)
entry_task = tk.Entry(root, width=30)   # 幅を20に設定
entry_task.pack(padx=10)

# ラベルとエントリ(日付)
label_due_date = tk.Label(root, text="年月日(yyyy-mm-dd)")
label_due_date.pack(padx=10)
entry_due_date = tk.Entry(root, width=30)   # 幅を20に設定
entry_due_date.pack(padx=10)

# ラベルとエントリー(開始時間)
label_due_time = tk.Label(root, text="開始時間(hh:mm)")
label_due_time.pack(padx=10)
entry_due_time = tk.Entry(root, width=30)   # 幅を20に設定
entry_due_time.pack(padx=10)

# ラベルとエントリー(終了時間)
label_end_time = tk.Label(root, text="終了時間(hh:mm)")
label_end_time.pack(padx=10)
entry_end_time = tk.Entry(root, width=30)   # 幅を20に設定
entry_end_time.pack(padx=10)


# 送信ボタン
submit_button = tk.Button(root, text="送信", command=submit_form)
submit_button.pack(pady=10, padx=10)

# リストボックスの作成
listbox = tk.Listbox(root, width=70)    # 幅を50に設定
listbox.pack(pady=10, padx=10)

# フォーム起動時にデータを表示
display_todos()

# Tkinterメインループ
root.mainloop()