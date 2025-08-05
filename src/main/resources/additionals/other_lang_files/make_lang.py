import json
import os
import shutil
from tqdm import tqdm

def get_all_file_mapping() -> dict[str,str]:
    all_lang_file = './other_lang_files/all_lang_path.json'
    with open(all_lang_file) as f:
        all_lang_dict:dict[str,dict[str,str|int]] = json.load(f)
    file_hash_mapping = {}
    for k,v in all_lang_dict.items():
        final_file_name = k.removeprefix('minecraft/lang/')
        file_hash_mapping[v.get('hash')] = final_file_name
    return file_hash_mapping    

def copy_files() -> None:
    file_mapping = get_all_file_mapping()
    lang_file_location = rf'{os.getenv("APPDATA")}\.minecraft\assets\objects'
    for root,_,files in tqdm(os.walk(lang_file_location)):
        for file in files:
            source_path = os.path.join(root,file)
            if file_mapping and file in file_mapping:
                destination_path = os.path.join('other_lang_files/copied_files',file_mapping[file])
                shutil.copy2(source_path,destination_path)
# def 

def main() -> None:
    copy_files()

if __name__ == "__main__":
    main()