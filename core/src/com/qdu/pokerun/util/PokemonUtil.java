package com.qdu.pokerun.util;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.qdu.pokerun.entity.Pokemon;
import com.qdu.pokerun.lib.LibPokemonDetails;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import jdk.vm.ci.meta.Local;

public class PokemonUtil {

    /**
     * 通过玩家ID获取该玩家的宝可梦
     *
     * @param playerID 玩家的ID
     * @return 一个LibGDX提供的数组，包括玩家所持有的宝可梦
     */
    public static Array<Pokemon> getPokemonsByPlayerId(String playerID) {
        Array<Pokemon> pokemons = new Array<>();
        if (playerID.equals("muyoo")) {
            //卷卷耳
            Pokemon p = new Pokemon();
            p.setNdex(427);
            p.setLevel(50);
            p.setExperience(8888);
            p.setFriendship(140);
            pokemons.add(p);
        } else if (playerID.equals("yxaa")) {
            //沙奈朵
            Pokemon p = new Pokemon();
            p.setNdex(282);
            p.setShiny(true);
            p.setLevel(50);
            p.setExperience(6666);
            p.setFriendship(70);
            pokemons.add(p);
        } else if (playerID.equals("gyf")) {
            //仙子伊布
            Pokemon p = new Pokemon();
            p.setNdex(700);
            p.setLevel(50);
            p.setExperience(7777);
            p.setFriendship(120);
            pokemons.add(p);
        } else if (playerID.equals("wujichen158")) {

            //巨金怪
            Pokemon p = new Pokemon();
            p.setNdex(376);
            p.setShiny(true);
            p.setLevel(50);
            p.setExperience(300);
            p.setFriendship(40);
            pokemons.add(p);

            //帝牙卢卡
            p = new Pokemon();
            p.setNdex(483);
            p.setLevel(50);
            p.setExperience(70);
            p.setFriendship(40);
            p.setCaughtBall(3);
            pokemons.add(p);

            //铁火辉夜
            p = new Pokemon();
            p.setNdex(797);
            p.setShiny(true);
            p.setLevel(50);
            p.setExperience(5000);
            p.setFriendship(70);
            pokemons.add(p);
        }
        return pokemons;
    }

    /**
     * 随机生成若干只宝可梦
     *
     * @param cnt 生成的个数
     * @return 一个LibGDX提供的数组，包括了所有生成结果
     */
    public static Array<Pokemon> generateRandomPokemon(int cnt) {
        Array<Pokemon> pokemons = new Array<>();
        for (int i = 0; i < cnt; i++) {
            Pokemon p = new Pokemon();
            p.setNdex(MathUtils.random(1, LibPokemonDetails.POKEDEX_NUM));
            //固定宝可梦为通常形态
            p.setForm("normal");
            //随机等级
            p.setLevel(MathUtils.random(1, 100));
            //随机经验值
            p.setExperience(MathUtils.random(1, 10000));
            //随机异色
            p.setShiny(MathUtils.randomBoolean());
            //被捕获的球随机
            p.setCaughtBall(MathUtils.random(0, 27));
            //随机亲密度
            p.setFriendship(MathUtils.random(0, 255));

            pokemons.add(p);
        }
        return pokemons;
    }

    /**
     * 根据形态差异等修正宝可梦图鉴编号所对应的图标名
     *
     * @param rawNDex 宝可梦对应的图鉴编号
     * @return 修正后的图标名
     */
    public static String processRawNDex(int rawNDex) {
        @SuppressWarnings("DefaultLocale")
        String str = String.format("%03d", rawNDex);
        //以下为需要特判的特殊宝可梦图标，不特判会NPE

        //判是否有雌雄性别差异。随机返回雌性或雄性
        if (judgeGenderDifference(rawNDex)) {
            boolean gender = MathUtils.randomBoolean();
            str += (gender ? "-male" : "-female");
        }
        //判是否有其他地区形态。若有则修正为通常形态
        if (judgeOtherForms(rawNDex)) {
            //特殊修正达摩狒狒
            if (rawNDex == 555) {
                str += "-standard";
            } else {
                str += "-normal";
            }
        }
        //判樱花儿晴天或通常形态。默认返回其通常形态
        if (rawNDex == 421) {
            str += "-overcast";
        }
        //判无壳海兔以及海兔兽的形态。随机返回东海或西海形态
        else if (rawNDex == 422 || rawNDex == 423) {
            boolean seaArea = MathUtils.randomBoolean();
            str += (seaArea ? "-west" : "-east");
        }
        //判骑拉帝纳起源或另类形态。默认返回另类形态
        else if (rawNDex == 487) {
            str += "-altered";
        }
        //判谢米飞行或陆地形态。默认返回其陆地形态
        else if (rawNDex == 492) {
            str += "-land";
        }
        //判阿尔宙斯石板。默认返回其无石板形态
        else if (rawNDex == 493) {
            str += "-normal";
        }
        //判断勇士鲈鱼颜色。随机返回其红色或者蓝色形态
        else if (rawNDex == 550) {
            boolean color = MathUtils.randomBoolean();
            if (color) {
                str += "-red";
            } else {
                str += "-blue";
            }
        }
        //判四季鹿和萌芽鹿的季节。随机返回一种季节的形态
        else if (rawNDex == 585 || rawNDex == 586) {
            int season = MathUtils.random(0, 3);
            switch (season) {
                case 0:
                    str += "-autumn";
                    break;
                case 1:
                    str += "-summer";
                    break;
                case 2:
                    str += "-spring";
                    break;
                default:
                    str += "-winter";
            }
        }
        //判三云的形态。随机返回化身或灵兽形态
        else if (rawNDex == 641 || rawNDex == 642 || rawNDex == 645) {
            boolean nadoForm = MathUtils.randomBoolean();
            str += (nadoForm ? "-incarnate" : "-therian");
        }
        //判凯路迪欧剑圣形态。默认返回通常形态
        else if (rawNDex == 647) {
            str += "-ordinary";
        }
        //判美洛耶塔形态。默认返回通常形态
        else if (rawNDex == 648) {
            str += "-aria";
        }
        //判彩粉蝶形态。默认返回花园花纹
        else if (rawNDex == 666) {
            str += "-meadow";
        }
        //判花蓓蓓、花叶蒂和花洁夫人的花朵颜色。默认返回红色
        else if (rawNDex == 669 || rawNDex == 670 || rawNDex == 671) {
            str += "-red";
        }
        //判坚盾剑怪形态。默认返回盾形态
        else if (rawNDex == 681) {
            str += "-shield";
        }
        //判哲尔尼亚斯是否处于活跃状态。默认返回通常状态
        else if (rawNDex == 716) {
            str += "-neutral";
        }
        //判胡帕形态。默认返回其束缚形态
        else if (rawNDex == 720) {
            str += "-confined";
        }
        //判花舞鸟舞者形态。随机返回其四种舞者之一
        else if (rawNDex == 741) {
            int dancer = MathUtils.random(0, 3);
            switch (dancer) {
                case 0:
                    str += "-baile";
                    break;
                case 1:
                    str += "-pau";
                    break;
                case 2:
                    str += "-pompom";
                    break;
                default:
                    str += "-sensu";
            }
        }
        //判鬃岩狼人形态。随机返回白昼、黄昏或夜晚形态
        else if (rawNDex == 745) {
            int time = MathUtils.random(0, 2);
            switch (time) {
                case 0:
                    str += "-midday";
                    break;
                case 1:
                    str += "-dusk";
                    break;
                default:
                    str += "-midnight";
            }
        }
        //判弱丁鱼形态。默认返回单条鱼模式
        else if (rawNDex == 746) {
            str += "-solo";
        }
        //判小陨星颜色。默认返回其未出壳形态
        else if (rawNDex == 774) {
            str += "-meteor";
        }
        //判颤弦蝾螈高低音。随机返回一种音量
        else if (rawNDex == 849) {
            boolean volume = MathUtils.randomBoolean();
            str += (volume ? "-amped" : "-lowkey");
        }
        //判霜奶仙。非常复杂，非常难顶。随机返回一种糖饰+口味
        else if (rawNDex == 869) {
            int candyDecoration = MathUtils.random(0, 6);
            switch (candyDecoration) {
                case 0:
                    str += "-berry";
                    break;
                case 1:
                    str += "-clover";
                    break;
                case 2:
                    str += "-flower";
                    break;
                case 3:
                    str += "-love";
                    break;
                case 4:
                    str += "-ribbon";
                    break;
                case 5:
                    str += "-star";
                    break;
                default:
                    str += "-strawberry";
            }
            int flavour = MathUtils.random(0, 8);
            switch (flavour) {
                case 0:
                    str += "-caramel";
                    break;
                case 1:
                    str += "-lemon";
                    break;
                case 2:
                    str += "-matcha";
                    break;
                case 3:
                    str += "-mint";
                    break;
                case 4:
                    str += "-rainbow";
                    break;
                case 5:
                    str += "-rubycream";
                    break;
                case 6:
                    str += "-rubyswirl";
                    break;
                case 7:
                    str += "-salted";
                    break;
                default:
                    str += "-vanilla";
            }
        }
        //判冰砌鹅脑袋的形态。默认返回其冰冻头形态
        else if (rawNDex == 875) {
            str += "-ice_face";
        }
        //判莫鲁贝可的饱了又饿。默认返回饱腹形态
        else if (rawNDex == 877) {
            str += "-fullbelly";
        }
        //判苍响和藏玛然特的王者或英雄形态。默认返回其英雄形态
        else if (rawNDex == 888 || rawNDex == 889) {
            str += "-hero";
        }
        //判无极汰那形态。默认返回其通常形态
        else if (rawNDex == 890) {
            str += "-ordinary";
        }
        //判武道熊师一击流或连击流。随机返回一种流派
        else if (rawNDex == 892) {
            boolean hitWay = MathUtils.randomBoolean();
            str += (hitWay ? "-rapidstrike" : "-singlestrike");
        }
        //判蕾冠王是否骑马。默认返回其未骑马的通常形态
        else if (rawNDex == 898) {
            str += "-ordinary";
        }

        //Pixelmon内的专属形态判断
        //判月石的月相。随机返回其一种月相
        else if (rawNDex == 337) {
            int moonPhase = MathUtils.random(0, 4);
            switch (moonPhase) {
                case 0:
                    str += "-crescent";
                    break;
                case 1:
                    str += "-full";
                    break;
                case 2:
                    str += "-gibbous";
                    break;
                case 3:
                    str += "-new_moon";
                    break;
                default:
                    str += "-quarter";
            }
        }
        return str;
    }

    /**
     * 判是宝可梦否有其他地区形态
     *
     * @param ndex 宝可梦对应的图鉴编号
     * @return 是否有地区形态
     */
    public static boolean judgeOtherForms(int ndex) {
        Integer[] i = new Integer[]{19, 20, 24, 25, 26, 27, 28, 37,
                38, 50, 51, 52, 53, 74, 75, 76, 77, 78, 79, 80, 83, 88,
                89, 103, 105, 110, 122, 144, 145, 146, 199, 222, 263,
                264, 351, 382, 383, 386, 399, 479, 551, 554,
                555, 562, 618, 646, 649, 773, 791, 800, 852,
                //酋雷姆，盖诺赛克特特判
                646, 649
        };
        List<Integer> has = Arrays.asList(i);
        return has.contains(ndex);
    }

    /**
     * 判断宝可梦是否有雌雄性别差异
     *
     * @param ndex 宝可梦对应的图鉴编号
     * @return 是否有雌雄差异
     */
    public static boolean judgeGenderDifference(int ndex) {
        Integer[] i = new Integer[]{202, 415, 449, 450, 521, 592, 593, 668, 678, 876};
        List<Integer> has = Arrays.asList(i);
        return has.contains(ndex);
    }

    /**
     * 将精灵球的ID转换为其对应的图标名称
     *
     * @param caughtBall 球种类的ID
     * @return 球对应的图标名称，也即为球的英文名
     */
    public static String convertBallType(int caughtBall) {
        switch (caughtBall) {
            case 0:
                return "poke";
            case 1:
                return "great";
            case 2:
                return "ultra";
            case 3:
                return "master";
            case 4:
                return "level";
            case 5:
                return "moon";
            case 6:
                return "friend";
            case 7:
                return "love";
            case 8:
                return "safari";
            case 9:
                return "heavy";
            case 10:
                return "fast";
            case 11:
                return "repeat";
            case 12:
                return "timer";
            case 13:
                return "nest";
            case 14:
                return "net";
            case 15:
                return "dive";
            case 16:
                return "luxury";
            case 17:
                return "heal";
            case 18:
                return "dusk";
            case 19:
                return "premier";
            case 20:
                return "sport";
            case 21:
                return "quick";
            case 22:
                return "park";
            case 23:
                return "lure";
            case 24:
                return "cherish";
            case 25:
                return "gs";
            case 26:
                return "beast";
            case 27:
                return "dream";
            default:
                return "available";
        }
    }

}
