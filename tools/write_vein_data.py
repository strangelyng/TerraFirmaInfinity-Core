import csv
import json
from generate_vein_configs import ALL_ROCKS, ALL_SANDS

from vein_dictionary_tfc import VEIN_DICT

def write_vein_data_to_csv(vein_dict: dict):
    fieldnames = ["Vein ID", "Type", "Distribution", "Rarity", "Density","Min Y", "Max Y", "Size", "Height", "Radius", "Skew", "Slant", "Sign","Projected?", "Project Offset?", "Near Lava?", "Biome", "Placement", "Indicator"] + ALL_ROCKS + ALL_SANDS

    with open("veins_output.csv", mode="w", newline="", encoding="utf-8") as csv_file:
        writer = csv.DictWriter(csv_file, fieldnames=fieldnames)
        writer.writeheader()

        for vein_key, vein_data in vein_dict.items():
            vein_config = vein_data.get("config", {})

            vein_type = vein_data.get("type", "").split(":")[-1] if ":" in vein_data.get("type", "") else vein_data.get("type", "")

            blocks = vein_config.get("blocks", [])
            ore_weights_dict = {}
            block_weights_dict = {}

            host_columns = {col: "" for col in (ALL_ROCKS + ALL_SANDS)}

            # Block entry nonsense
            for block_entry in blocks:
                replace_targets = block_entry.get("replace", [])
                with_entries = block_entry.get("with", [])

                for target in replace_targets:
                    if target.startswith("tfc:rock/raw/"):
                        rock_name = target.replace("tfc:rock/raw/", "")
                        if rock_name in host_columns:
                            host_columns[rock_name] = "x"
                    elif target.startswith("tfc:sand/"):
                        sand_color = target.replace("tfc:sand/")
                        if sand_color in host_columns:
                            current = host_columns[sand_color]
                            host_columns[sand_color] = "x" if current == "sandstone" else "sand"
                    elif target.startswith("tfc:raw_sandstone/"):
                        sand_color = target.replace("tfc:raw_sandstone/")
                        if sand_color in host_columns:
                            current = host_columns[sand_color]
                            host_columns[sand_color] = "x" if current == "sand" else "sandstone"

                for entry in with_entries:
                    block_id = entry.get("block", "")
                    weight = entry.get("weight", 100)

                    if "ore" in block_id and ("sandstone" in block_id or any(f"{r}" in block_id for r in ALL_ROCKS) or any(f"{s}" in block_id for s in ALL_SANDS)): # LMAO
                        material = block_id.split(":")[-1]

                        for r in ALL_ROCKS:
                            material = material.replace(f"{r}_", "")

                        for s in ALL_SANDS:
                            material = material.replace(f"{s}_", "")

                        material = material.replace("_ore", "")

                        if ":" in block_id and not block_id.startswith("gtceu:"):
                            material = f"{block_id.split(':')[0]}:{material}"

                        ore_weights_dict[material] = weight
                    else:
                        block_weights_dict[block_id] = weight

            ore_segment = " / ".join([f"{k} {v}" for k, v in ore_weights_dict.items()])
            block_segment = " / ".join([f"{k} {v}" for k, v in block_weights_dict.items()])
            dist_str = f"{ore_segment} || {block_segment}" if block_segment else ore_segment

            if vein_type == "pipe_vein":
                skew_str = f"{vein_config.get('min_skew', 0)},{vein_config.get('max_skew', 0)}"
                slant_str = f"{vein_config.get('min_slant', 0)},{vein_config.get('max_slant', 0)}"
            else:
                skew_str = ""
                slant_str = ""

            placements = vein_data.get("placement", [])
            placement_str = ""
            if placements:
                placement_str = ", ".join(json.dumps(placement) for placement in placements)

            indicator_dict = vein_config.get("indicator", {})
            indicator_str = ""
            if indicator_dict:
                block_map = {b["block"]: b["weight"] for b in indicator_dict.get("blocks", [])}
                indicator_str = f"{block_map},{indicator_dict.get('rarity', 0)},{indicator_dict.get('depth', 0)},{indicator_dict.get('underground_rarity', 0)},{indicator_dict.get('underground_count', 0)}"

            row = {
                "Vein ID": vein_key,
                "Type": vein_type,
                "Distribution": dist_str,
                "Rarity": vein_config.get("rarity", ""),
                "Density": vein_config.get("density", ""),
                "Min Y": vein_config.get("min_y", ""),
                "Max Y": vein_config.get("max_y", ""),
                "Size": vein_config.get("size", "") if vein_type in ("cluster_vein", "disc_vein") else "",
                "Height": vein_config.get("height", "") if vein_type in ("disc_vein", "pipe_vein") else "",
                "Radius": vein_config.get("radius", "") if vein_type == "pipe_vein" else "",
                "Skew": skew_str,
                "Slant": slant_str,
                "Sign": vein_config.get("sign", "") if vein_type == "pipe_vein" else "",
                "Projected?": "True" if vein_config.get("project") else "",
                "Project Offset?": "True" if vein_config.get("project_offset") else "",
                "Near Lava?": "True" if vein_config.get("near_lava") else "",
                "Biome": vein_data.get("biome_filter", ""),
                "Placement": placement_str,
                "Indicator": indicator_str
            }

            row.update(host_columns)

            writer.writerow(row)

    print(f"Successfully wrote vein data entries to veins.csv")

def main():
    vein_dictionary = VEIN_DICT

    write_vein_data_to_csv(vein_dictionary)


if __name__ == "__main__":
    main()