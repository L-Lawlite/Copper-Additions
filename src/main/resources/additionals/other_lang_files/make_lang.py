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

def make_actual_lang_file(output_folder:str):
    with open('other_lang_files/keys_to_copy.json') as f:
        keys_to_copy = json.load(f)
    with open('other_lang_files/keys_to_ignore_rename.json') as f:
        keys_to_not_rename = json.load(f) 
    for root,_,files in tqdm(os.walk('./other_lang_files/copied_files')):
        for file in files:
            final_data = {}
            with open(os.path.join(root,file), encoding="utf-8") as f:
                data:dict[str,str] = json.load(f)
            temp_data = {k:v for k,v in data.items() if k in keys_to_copy}
            final_data = {(k.replace('minecraft',mod_id) if k not in keys_to_not_rename else k):v for k,v in temp_data.items()} 
            with open(f'{output_folder}/{file}', 'w', encoding="utf-8") as writen_file:
                json.dump(final_data,writen_file,indent=4, ensure_ascii=False)



def main() -> None:
    # copy_files()
    make_actual_lang_file(r'D:\Minecraft mods\Copper-Additions\src\main\resources\assets\copper_additions\lang')

if __name__ == "__main__":
    mod_id = 'copper_additions'
    main()