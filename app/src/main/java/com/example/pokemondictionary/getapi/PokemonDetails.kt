package com.example.pokemondictionary.getapi

import com.google.gson.annotations.SerializedName

data class PokemonDetails(
    @SerializedName("id")
    val id: Int?, // 포켓몬 ID값

    @SerializedName("name")
    val name: String?, // 포켓몬 이름

    @SerializedName("base_experience")
    val baseExperience: Int?, // 해당 포켓몬을 쓰러뜨렸을 때의 기본 경험치

    @SerializedName("height")
    val height: Int?, // 포켓몬 키 (height 값은 해당 포켓몬의 키 * 10으로 되어 있음)

//    @SerializedName("is_default")
//    val isDefault: Boolean, // 정식 시리즈 등장 여부, 도감 제작에 필요 없어보임

    @SerializedName("order")
    val order: Int?, // 포켓몬 전체의 정렬 순서

    @SerializedName("weight")
    val weight: Int?, // 포켓몬 무게

    @SerializedName("abilities")
    val abilities: List<PokemonAbility?>?, // 포켓몬 특성 리스트

    @SerializedName("forms")
    val forms: List<PokemonForm?>?,

    @SerializedName("game_indices")
    val gameIndices: List<PokemonGameIndices?>?,

    @SerializedName("held_items")
    val heldItems: List<PokemonHeldItems?>?, // 포켓몬과 조우 시, 소지할 수 있는 물건

    @SerializedName("location_area_encounters")
    val locationAreaEncounters: String?,

    @SerializedName("moves")
    val moves: List<PokemonMoves?>?,

//    @SerializedName("past_type")
//    val pastTypes: List<PokemonTypePast>, // 포켓몬 진화전 타입, 필요 없음

    @SerializedName("species")
    val species: PokemonSpecies?,

    @SerializedName("sprites")
    val sprites: PokemonSprites?,

    @SerializedName("stats")
    val stats: List<PokemonStats?>?,

    @SerializedName("types")
    val types: List<PokemonTypes?>?
) {
    data class PokemonAbility(
        @SerializedName("is_hidden")
        val isHidden: Boolean?, // 해당 어빌리티의 공개 여부
        @SerializedName("slot")
        val slot: Int?, // 어빌리티 슬롯, 일반 1~2, 히든 3 최대 개수 3개
        @SerializedName("ability")
        val ability: Ability
    ) {
        data class Ability(
            @SerializedName("name")
            val name: String?, // 어빌리티 명
            @SerializedName("url")
            val url: String?
        )
    }

    data class PokemonForm(
        @SerializedName("name")
        val name: String?,
        @SerializedName("url")
        val url: String?
    )

    data class PokemonGameIndices(
        @SerializedName("game_index")
        val gameIndex: Int?,
        @SerializedName("version")
        val version: Version
    ) {
        data class Version(
            @SerializedName("name")
            val name: String?,
            @SerializedName("url")
            val url: String?
        )
    }

    data class PokemonHeldItems(
        @SerializedName("item")
        val item: Item?,
        @SerializedName("version_details")
        val versionDetails: List<VersionDetails?>?
    ) {
        data class Item(
            @SerializedName("name")
            val name: String?,
            @SerializedName("url")
            val url: String?
        )

        data class VersionDetails(
            @SerializedName("rarity")
            val rarity: Int?,
            @SerializedName("version")
            val version: Version?
        ) {
            data class Version(
                @SerializedName("name")
                val name: String?,
                @SerializedName("url")
                val url: String?
            )
        }
    }

    data class PokemonMoves(
        @SerializedName("move")
        val move: Move?,
        @SerializedName("version_group_details")
        val versionGroupDetails: List<VersionGroupDetails?>?
    ) {
        data class Move(
            @SerializedName("name")
            val name: String?,
            @SerializedName("url")
            val url: String?
        )

        data class VersionGroupDetails(
            @SerializedName("level_learned_at")
            val levelLearnedAt: Int?,
            @SerializedName("move_learn_method")
            val moveLearnMethod: MoveLeanMethod?,
            @SerializedName("version_group")
            val versionGroup: VersionGroup?
        ) {
            data class MoveLeanMethod(
                @SerializedName("name")
                val name: String?,
                @SerializedName("url")
                val url: String?
            )

            data class VersionGroup(
                @SerializedName("name")
                val name: String?,
                @SerializedName("url")
                val url: String?
            )
        }
    }

    data class PokemonSpecies(
        @SerializedName("name")
        val name: String?,
        @SerializedName("url")
        val url: String?
    )

    data class PokemonSprites(
        @SerializedName("front_default")
        val frontDefault: String?,
        @SerializedName("other")
        val other: Other?
    ) {
        data class Other(
            @SerializedName("official-artwork")
            val officialArtwork: OfficialArtwork?
        ) {
            data class OfficialArtwork(
                @SerializedName("front_default")
                val frontDefault: String?
            )
        }
    }

    data class PokemonStats(
        @SerializedName("base_stat")
        val baseStat: Int?,
        @SerializedName("effort")
        val effort: Int?,
        @SerializedName("stat")
        val stat: Stat
    ) {
        data class Stat(
            @SerializedName("name")
            val name: String?,
            @SerializedName("url")
            val url: String?
        )
    }

    data class PokemonTypes(
        @SerializedName("slot")
        val slot: Int?,
        @SerializedName("type")
        val type: Type?
    ) {
        data class Type(
            @SerializedName("name")
            val name: String?,
            @SerializedName("url")
            val url: String?
        )
    }

}

