from typing import List, Dict, Any

class ClipScorer:
    def rank_clips(self, candidates: List[Dict[str, Any]]) -> List[Dict[str, Any]]:
        ranked = []
        for idx, item in enumerate(candidates):
            text_len = len(item.get("text", ""))
            # Multi-factor score heuristic: speech density + length balance
            base_score = 0.75 + min(0.20, text_len / 500.0)
            item["score"] = round(base_score, 2)
            ranked.append(item)

        # Sort clips by highest score
        ranked.sort(key=lambda x: x["score"], reverse=True)
        return ranked
