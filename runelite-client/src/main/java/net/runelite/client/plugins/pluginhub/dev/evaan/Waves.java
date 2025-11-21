package net.runelite.client.plugins.pluginhub.dev.evaan;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.runelite.client.ui.overlay.components.LineComponent;

public interface Waves {
    static LineComponent[] parseLines(String input) {
        return input.lines()
                .map(line -> LineComponent.builder().left(line).build())
                .toArray(LineComponent[]::new);
    }

    @Getter
    @RequiredArgsConstructor
    enum Wave4 {
        REGULAR("Regular", parseLines("2-4-2(27) //\n0-0-0-8")),
        ONEXOS("1x OS", parseLines("2-5-4 //\n0-0-0-7")),
        CUSTOM("Custom", null);

        private final String name;
        private final LineComponent[] lines;

        @Override
        public String toString() {
            return name;
        }
    }

    @Getter
    @RequiredArgsConstructor
    enum Wave5 {
        REGULAR("Regular", parseLines("1-3(18)-2-2(27) //\n0-0-0-0-8")),
        ONEXOS("1x OS", parseLines("2-5-2(21)-3(30) //\n0-0-0-0-7")),
        CFIVE("C5", parseLines("Alch horn, 6x os\n3(22)[43]-7-4(21)-6-8\nIf 12s not on Coll:\n8-2(21)-4(21)-6-8")),
        CUSTOM("Custom", null);

        private final String name;
        private final LineComponent[] lines;

        @Override
        public String toString() {
            return name;
        }
    }

    @Getter
    @RequiredArgsConstructor
    enum Wave6 {
        REGULAR("Regular", parseLines("2/3(18)-4(24)-1-1 //\nRestock 3x os + 1x reg\n0-0-1-1-X-X")),
        ONEXOS("1x OS", parseLines("3(18)-5-2(21)-2 //\nRestock 3x + 1x reg\n0-0-0-1-9-11\n")),
        TWOXOS("2x OS", parseLines("3(18)-5-3-4 //\nRestock 3x os + 1x reg\n0-0-10-12\n")),
        CUSTOM("Custom", null);

        private final String name;
        private final LineComponent[] lines;

        @Override
        public String toString() {
            return name;
        }
    }

    @Getter
    @RequiredArgsConstructor
    enum Wave7 {
        REGULAR("Regular", parseLines("2-5(27)-1-1 //\n1(33)-0-1(33)-1-4-1\nRestock 13/13 or 1x os //\n1-3-7")),
        TWOXOS("2x OS", parseLines("2(27)-6-6-1 //\nRestock 4x os\n0-0-0-1(39)-2-9-11")),
        CUSTOM("Custom", null);

        private final String name;
        private final LineComponent[] lines;

        @Override
        public String toString() {
            return name;
        }
    }

    @Getter
    @RequiredArgsConstructor
    enum Wave8 {
        REGULAR("Regular", parseLines("2-4(21)-1/2(27)-1 //\n1-1[42]-1/0-1-3(42)-1/2 //\nRestock\n0-0-0-0-6/5-X")),
        ONEXOS("1x OS", parseLines("1-9-1-1 //\n2-0-1-1(36)-2-1\nRestock 13/13 or 1-2x os //\n0-0-3-10")),
        TWOXOS("2x OS", parseLines("4(24)-9-1-1 //\n0-0-1-2(39)-1-1\nRestock 4x os + 1x reg\n0-0-3(51)-5(51)[57]-X")),
        CUSTOM("Custom", null);

        private final String name;
        private final LineComponent[] lines;

        @Override
        public String toString() {
            return name;
        }
    }

    @Getter
    @RequiredArgsConstructor
    enum Wave9 {
        REGULAR("Regular", parseLines("2(18)-4/5(18)-1-1 //\n1[54]-2/3-1(33)-1(42)-1-1-1 //\nRestock\n0-0-0-0-1[69]-2[69]-5[75]-X")),
        ONEXOS("1x OS", parseLines("2-8(21)-1-1 //\n1-0-1(33)-1(45)-2(45)-1-1 //\nRestock 4x os\n0-0-3-5-X")),
        BNINE("B9 (1x OS)", parseLines("9(27)-1-1-1 //\n0-1-1(42)-2[45](45)-1-1-1\nRestock 4x\n0-0-5-6-7[42]")),
        CUSTOM("Custom", null);

        private final String name;
        private final LineComponent[] lines;

        @Override
        public String toString() {
            return name;
        }
    }

    @Getter
    @RequiredArgsConstructor
    enum Wave10 {
        REGULAR("Regular", parseLines("2-4/5(27)-1-1 //\n1(33)-1/0-1(33)-1-4(51)-1\nRestock 13/13 or 2x os //\n1-3-7")),
        TWOXOS("2x OS", parseLines("3(25)-5(27)-6-1 //\n0-0-0-2 \nRestock 5x os\n0-8-8-10")),
        CUSTOM("Custom", null);

        private final String name;
        private final LineComponent[] lines;

        @Override
        public String toString() {
            return name;
        }
    }
}
