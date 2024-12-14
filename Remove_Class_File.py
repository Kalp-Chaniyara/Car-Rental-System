import os

def delete_class_file():
    current_directory = os.getcwd()

    files_iterator = os.listdir(current_directory)

    for item in files_iterator:
        parts = item.split(".")
        if(parts[1] == "class"):
            os.remove(os.path.join(current_directory, item))
            print(item + " deleted")

if __name__ == "__main__":
    delete_class_file()